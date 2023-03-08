package kg.manas.crm.annotations;

import java.lang.annotation.*;
@Repeatable(value = Mappings.class)
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
public @interface Mapping {
    String target();
    String source();
}
