package kg.manas.crm.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PROCESS_STEPS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "process_step_seq", allocationSize = 1)
public class ProcessStep extends BaseEntity {
    Boolean isInitialStep;
    @ManyToOne
    @JoinColumn(name = "NEXT_STEP_ID")
    ProcessStep nextStep;
    @ManyToOne
    @JoinColumn(name = "ACTION_DEFINITION_ID")
    ActionDefinition actionDefinition;
    @ManyToOne
    @JoinColumn(name = "PROCESS_ID")
    Process process;

}
