package kg.manas.crm.entities;
import javax.persistence.*;

import kg.manas.crm.models.ServiceCategoryModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "SERVICE_CATEGORIES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "service_categories_seq", allocationSize = 1)
public class ServiceCategory extends BaseEntity {
    String name;
}
