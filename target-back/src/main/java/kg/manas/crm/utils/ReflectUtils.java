package kg.manas.crm.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@UtilityClass
public class ReflectUtils {

    public static List<Field> getClassFields(Class<?> type) {
        return getAllFields(new ArrayList<>(), type);
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static void setFieldValue(Field fieldToSet, Object newValue, Object targetObject ) {
        if (fieldToSet == null || targetObject == null) return;

        boolean initialAccessibleState = false;
        try {
            initialAccessibleState = fieldToSet.isAccessible();
            if (!initialAccessibleState) fieldToSet.setAccessible(true);

            fieldToSet.set(targetObject, newValue);

        } catch (IllegalAccessException e) {
            log.error("Error while setting {} field value to {} type object", fieldToSet.getName(), targetObject.getClass().getSimpleName(), e);
        } finally {
            if (!initialAccessibleState) fieldToSet.setAccessible(false);
        }
    }

    public Object getFieldValueByName(String fieldName, Object sourceObject) {
        for (Field classField : getClassFields(sourceObject.getClass())) {
            if (!classField.getName().equals(fieldName)) continue;

            boolean initialAccessibleState = false;
            try {

                initialAccessibleState = classField.isAccessible();
                if (!initialAccessibleState) classField.setAccessible(true);

                return classField.get(sourceObject);

            } catch (IllegalAccessException e) {
                log.warn("Error while getting value from field {} in {} class", fieldName, sourceObject.getClass(), e);
            } finally {
                if (!initialAccessibleState) classField.setAccessible(false);
            }
        }

        return null;
    }

    public Object getFieldByName(String fieldName, Object sourceObject) {
        for (Field classField : getClassFields(sourceObject.getClass())) {
            if (!classField.getName().equals(fieldName)) continue;

            boolean initialAccessibleState = false;
            try {

                initialAccessibleState = classField.isAccessible();
                if (!initialAccessibleState) classField.setAccessible(true);

                return classField;

            } finally {
                if (!initialAccessibleState) classField.setAccessible(false);
            }
        }

        return null;
    }


    public static Object getFieldValue(Field fieldToGet, Object targetObject ) {
        if (fieldToGet == null || targetObject == null) return null;

        boolean initialAccessibleState = false;
        try {
            initialAccessibleState = fieldToGet.isAccessible();
            if (!initialAccessibleState) fieldToGet.setAccessible(true);

            return fieldToGet.get(targetObject);

        } catch (IllegalAccessException e) {
            log.error("Error while setting {} field value to {} type object", fieldToGet.getName(), targetObject.getClass().getSimpleName(), e);
        } finally {
            if (!initialAccessibleState) fieldToGet.setAccessible(false);
        }

        throw new IllegalArgumentException("Unable to extract value for field " + fieldToGet.getName() + " from object " + targetObject);
    }

    public static Object findFirstAnnotatedFieldValue(Object targetClass, Class<? extends Annotation> annotation) {
        if (targetClass == null) return null;

        Object fieldValue = null;

        for (Field targetField : ReflectUtils.getClassFields(targetClass.getClass())) {
            Annotation foundAnnotations = targetField.getAnnotation(annotation);
            if (foundAnnotations != null) {
                boolean initialAccessibleState = false;
                try {
                    initialAccessibleState = targetField.isAccessible();
                    if (!initialAccessibleState) targetField.setAccessible(true);

                    fieldValue = targetField.get(targetClass);
                    break;

                } catch (IllegalAccessException e) {
                    log.error("Error while extracting {} from object", annotation, e);
                } finally {
                    if (!initialAccessibleState) targetField.setAccessible(false);
                }
            }
        }

        return fieldValue;
    }
}
