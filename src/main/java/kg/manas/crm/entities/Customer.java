package kg.manas.crm.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
