package kg.manas.crm.entities;

import kg.manas.crm.enums.ParameterType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PROCESS_STEP_PARAMS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "process_step_param_seq", allocationSize = 1)
public class ProcessStepParam extends BaseEntity{
    String value;
    @ManyToOne
    @JoinColumn(name = "PROCESS_STEP")
    ProcessStep processStep;

    ParameterType parameterType;
}
