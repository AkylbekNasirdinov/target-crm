package kg.manas.crm.models;

import kg.manas.crm.enums.ParameterType;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProcessStepParamModel {
    Long id;

    String value;

    ProcessStepModel processStep;

    ParameterType parameterType;
}
