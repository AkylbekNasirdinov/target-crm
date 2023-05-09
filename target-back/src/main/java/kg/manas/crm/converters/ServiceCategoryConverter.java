package kg.manas.crm.converters;

import kg.manas.crm.entities.ServiceCategory;
import kg.manas.crm.models.ServiceCategoryModel;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServiceCategoryConverter extends Converter<ServiceCategory, ServiceCategoryModel>{

    public ServiceCategoryConverter(Reflections reflections) {
        super(ServiceCategory.class, ServiceCategoryModel.class, reflections);
    }
}
