package kg.manas.crm.converters;

import kg.manas.crm.entities.UserServices;
import kg.manas.crm.models.UserServicesModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class UserServicesConverter extends Converter<UserServices, UserServicesModel>{
    public UserServicesConverter(Reflections reflections) {
        super(UserServices.class, UserServicesModel.class, reflections);
    }
}
