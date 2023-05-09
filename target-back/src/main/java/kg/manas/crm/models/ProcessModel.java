package kg.manas.crm.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProcessModel {
    Long id;
    String name;
    ServiceModel offeredService;
    Boolean isActive;
    String executionCron;
    LocalDateTime lastExecutionDate;
}
