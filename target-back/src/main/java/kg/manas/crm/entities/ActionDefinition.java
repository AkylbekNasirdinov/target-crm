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
@Table(name = "ACTIONS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "actions_seq", allocationSize = 1)
public class ActionDefinition extends BaseEntity {
    String name;
    String description;
    String actionQualifier;
    @Enumerated(EnumType.STRING)
    ParameterType requiredParameter;
}
