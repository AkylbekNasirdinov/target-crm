package kg.manas.crm.models;

import kg.manas.crm.entities.ActionDefinition;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProcessStepModel {
    Long id;
    Boolean isInitialStep;
    ProcessStepModel nextStep;
    ActionDefinition actionDefinition;
    ProcessModel process;
}
