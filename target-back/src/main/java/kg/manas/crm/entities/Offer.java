package kg.manas.crm.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "OFFERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "offers_seq", allocationSize = 1)
public class Offer extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "PURCHASED_SERVICE")
    Service service;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    Customer customer;
    @Column(name = "IS_CONFIRMED")
    Boolean isConfirmed;
    @ManyToOne
    @JoinColumn(name = "PROCESS_ID")
    Process process;
}
