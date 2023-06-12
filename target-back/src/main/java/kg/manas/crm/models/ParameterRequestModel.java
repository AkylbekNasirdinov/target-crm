package kg.manas.crm.models;

import kg.manas.crm.enums.ParameterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParameterRequestModel {
    Long id;

    String value;
    @NotNull
    Long stepId;

    ParameterType parameterType;
}
