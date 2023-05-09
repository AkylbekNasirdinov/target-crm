package kg.manas.crm.converters;

import kg.manas.crm.annotations.Mapping;
import kg.manas.crm.entities.Service;
import kg.manas.crm.models.ServiceModel;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Mapping(target = "category", source = "serviceCategoryModel")
public class ServiceConverter extends Converter<Service, ServiceModel>{
    public ServiceConverter(Reflections reflections, ApplicationContext applicationContext) {
        super(Service.class, ServiceModel.class, reflections, applicationContext);
    }
}
