package kg.manas.crm.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "PROCESSES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "process_seq", allocationSize = 1)
public class Process extends BaseEntity{
    String name;
    @ManyToOne
    @JoinColumn(name = "OFFERED_SERVICE")
    Service offeredService;
    Boolean isActive;
    String executionCron;
    LocalDateTime lastExecutionDate;

}
