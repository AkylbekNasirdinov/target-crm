package kg.manas.crm.converters;

import kg.manas.crm.entities.User;
import kg.manas.crm.models.UserModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
@Component
public class UserConverter extends Converter<User, UserModel> {
    public UserConverter(Reflections reflections) {
        super(User.class, UserModel.class, reflections);
    }
}
