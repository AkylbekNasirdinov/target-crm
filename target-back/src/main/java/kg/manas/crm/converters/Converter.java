package kg.manas.crm.converters;

import kg.manas.crm.annotations.Mapping;
import kg.manas.crm.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.Fields;
import static org.reflections.ReflectionUtils.get;

@Slf4j
@Valid
@Component
public abstract class Converter<Entity, Model> {

    private final Class<Entity> entityClass;
    private final Class<Model> modelClass;
    private final Set<Field> fieldsOfEntity;
    private final Set<Field> fieldsOfModel;
    private final Map<String, String> mappings;
    private final Reflections reflections;
    private Set<Class<? extends Converter>> converters;
    @Autowired
    private ApplicationContext applicationContext;

    public Converter(@NotNull Class<Entity> entityClass, @NotNull Class<Model> modelClass, Reflections reflections) {
        this.reflections = reflections;
        this.entityClass = entityClass;
        this.modelClass = modelClass;
        this.fieldsOfEntity = get(Fields.of(entityClass));
        this.fieldsOfModel = get(Fields.of(modelClass));
        this.converters = reflections.getSubTypesOf(Converter.class);
        this.mappings = this.getMappings();
    }


    public Model convetToModel(Entity entity) {
        Model instance = null;
        try {
            instance = modelClass.getDeclaredConstructor().newInstance();
            for (Field entityField : fieldsOfEntity) {
                Optional<Field> modelFieldOptional = fieldsOfModel.stream()
                        .filter(modelField -> modelField.getName().equals(entityField.getName())
                                || modelField.getName().equals(mappings.get(entityField.getName())))
                        .findFirst();
                if (modelFieldOptional.isEmpty())
                    continue;
                Object targetValue = ReflectUtils.getFieldValue(entityField, entity);
                if (!modelFieldOptional.get().getType().equals(entityField.getType())) {
                    Converter converter = getConverterByTypes((Class<Entity>) entityField.getType(), (Class<Model>) modelFieldOptional.get().getType());
                    targetValue = converter.convetToModel(targetValue);
                }
                ReflectUtils.setFieldValue(modelFieldOptional.get(), targetValue, instance);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return instance;
    }

    public Entity convertToEntity(Model model) {
        Entity instance = null;
        try {
            instance = entityClass.getDeclaredConstructor().newInstance();
            for (Field modelField : fieldsOfModel) {
                Optional<Field> entityFieldOptional = fieldsOfEntity.stream()
                        .filter(entityField -> entityField.getName().equals(modelField.getName())
                                || entityField.getName().equals(getKey(mappings, modelField.getName())))
                        .findFirst();
                if (entityFieldOptional.isEmpty())
                    continue;
                Object targetValue = ReflectUtils.getFieldValue(modelField, model);
                if (!entityFieldOptional.get().getType().equals(modelField.getType())) {
                    Converter converter = getConverterByTypes((Class<Entity>) modelField.getType(), (Class<Model>) entityFieldOptional.get().getType());
                    targetValue = converter.convetToModel(targetValue);
                }
                ReflectUtils.setFieldValue(entityFieldOptional.get(), targetValue, instance);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return instance;
    }


    private <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Converter getConverterByTypes(Class<Entity> entityClass, Class<Model> modelClass) {
        return this.getConverterFromClass(getConverterClass(entityClass, modelClass));
    }

    private Class<? extends Converter> getConverterClass(Class<Entity> entityClass, Class<Model> modelClass) {
        return this.converters.stream()
                .filter(subConverter -> isConverterOfClasses(entityClass, modelClass, subConverter)).findFirst().orElseThrow();
    }

    private boolean isConverterOfClasses(Class<Entity> entityClass, Class<Model> modelClass, Class<? extends Converter> subConverterClass) {
        try {
            Converter converter = getConverterFromClass(subConverterClass);
            return converter.modelClass.equals(modelClass) && converter.entityClass.equals(entityClass);
        } catch (Exception e) {
            return false;
        }
    }

    private Converter getConverterFromClass(Class<? extends Converter> subConverterClass) {
        return applicationContext.getBean(subConverterClass);
    }

    private Map<String, String> getMappings() {
        return Arrays.stream(getClass().getAnnotationsByType(Mapping.class))
                .collect(Collectors.toMap(Mapping::source, Mapping::target));
    }

}
