package kg.manas.crm.converters;

import kg.manas.crm.entities.UserServices;
import kg.manas.crm.models.UserServicesModel;
import org.springframework.stereotype.Component;

@Component
public class UserServicesConverter extends Converter<UserServices, UserServicesModel>{
    public UserServicesConverter() {
        super(UserServices.class, UserServicesModel.class);
    }
}
