package kg.manas.crm.converters;

import kg.manas.crm.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.reflections.ReflectionUtils.Fields;
import static org.reflections.ReflectionUtils.get;
@Slf4j
@Valid
public abstract class Converter<Entity, Model> {

    private final Class<Entity> entityClass;
    private final Class<Model> modelClass;
    private final Set<Field> fieldsOfEntity;
    private final Set<Field> fieldsOfModel;
    private final Map<String, String> mappings;

    Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("kg.manas.crm.converters"))
            .setScanners(Scanners.values()));

    public Converter(@NotNull Class<Entity> entityClass, @NotNull Class<Model> modelClass, Map<String, String> mappings) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;
        this.fieldsOfEntity = get(Fields.of(entityClass));
        this.fieldsOfModel = get(Fields.of(modelClass));
        this.mappings = mappings;
    }

    public Converter(@NotNull Class<Entity> entityClass, @NotNull Class<Model> modelClass) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;
        this.fieldsOfEntity = get(Fields.of(entityClass));
        this.fieldsOfModel = get(Fields.of(modelClass));
        this.mappings = new HashMap<>();
    }


    public Model convetToModel(Entity entity){
        Model instance = null;
        try {
            instance = modelClass.getDeclaredConstructor().newInstance();
            for (Field entityField : fieldsOfEntity) {
                Optional<Field> modelFieldOptional = fieldsOfModel.stream()
                        .filter(modelField -> modelField.getName().equals(entityField.getName())
                                || modelField.getName().equals(mappings.get(entityField.getName())))
                        .findFirst();
                if (modelFieldOptional.isEmpty())
                    throw new NoSuchFieldException(MessageFormat.format("can not find field with name {0}", entityField.getName()));
                Object targetValue = ReflectUtils.getFieldValue(entityField, entity);
                if (!modelFieldOptional.get().getType().equals(entityField.getType())){
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

    public Entity convertToEntity(Model model) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException{
        Entity instance = null;
        try {
            instance = entityClass.getDeclaredConstructor().newInstance();
            for (Field modelField : fieldsOfModel) {
                Optional<Field> entityFieldOptional = fieldsOfEntity.stream()
                        .filter(entityField -> entityField.getName().equals(modelField.getName())
                                || entityField.getName().equals(getKey(mappings, modelField.getName())))
                        .findFirst();
                if (entityFieldOptional.isEmpty())
                    throw new NoSuchFieldException(MessageFormat.format("can not find field with name {0}", modelField.getName()));
                Object targetValue = ReflectUtils.getFieldValue(modelField, model);
                if (!entityFieldOptional.get().getType().equals(modelField.getType())){
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
      return reflections.getSubTypesOf(Converter.class).stream()
               .filter(subConverter -> isConverterOfClasses(entityClass, modelClass, subConverter))
               .map(this::getConverterFromClass).findFirst().orElseThrow();
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
        try {
            return (Converter)subConverterClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
