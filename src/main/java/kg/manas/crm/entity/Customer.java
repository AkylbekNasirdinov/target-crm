package kg.manas.crm.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "CUSTOMERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "customers_seq", allocationSize = 1)
public class Customer extends BaseEntity {
    @Column(name = "NAME")
    String name;
}
