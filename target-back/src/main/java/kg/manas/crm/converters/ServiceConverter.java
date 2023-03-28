package kg.manas.crm.converters;

import kg.manas.crm.annotations.Mapping;
import kg.manas.crm.entities.Service;
import kg.manas.crm.models.ServiceModel;
import org.springframework.stereotype.Component;

@Component
@Mapping(target = "category", source = "serviceCategoryModel")
public class ServiceConverter extends Converter<Service, ServiceModel>{
    public ServiceConverter() {
        super(Service.class, ServiceModel.class);
    }
}
