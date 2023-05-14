package kg.manas.crm.entities;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "PURCHASES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "purchases_seq", allocationSize = 1)
public class Purchase extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    Customer user;
    @ManyToOne
    @JoinColumn(name = "PURCHASED_SERVICE")
    Service purchasedService;
}
