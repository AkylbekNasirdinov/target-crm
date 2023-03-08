package kg.manas.crm.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "SERVICES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "services_seq", allocationSize = 1)
public class Service extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "CATEGORY")
    ServiceCategory category;
    @Column(name = "PRICE")
    BigDecimal price;
    @Column(name = "NAME")
    String name;
    @Column(name = "DESCRIPTION")
    String description;
}
