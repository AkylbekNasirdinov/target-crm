package kg.manas.crm.converters;

import kg.manas.crm.entities.ServiceCategory;
import kg.manas.crm.models.ServiceCategoryModel;
import org.springframework.stereotype.Component;

@Component
public class ServiceCategoryConverter extends Converter<ServiceCategory, ServiceCategoryModel>{

    public ServiceCategoryConverter() {
        super(ServiceCategory.class, ServiceCategoryModel.class);
    }
}
