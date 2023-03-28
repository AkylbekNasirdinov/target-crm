package kg.manas.crm.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServicesModel {

    CustomerModel customer;
    ServiceModel purchasedService;
}
