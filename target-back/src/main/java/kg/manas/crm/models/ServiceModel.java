package kg.manas.crm.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceModel {

    Long id;
    ServiceCategoryModel category;
    String name;
    String description;
    BigDecimal price;
}
