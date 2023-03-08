package kg.manas.crm.converters;

import kg.manas.crm.entities.User;
import kg.manas.crm.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class UserConverter extends Converter<User, UserModel> {
    public UserConverter() {
        super(User.class, UserModel.class, new HashMap<>());
    }
}
