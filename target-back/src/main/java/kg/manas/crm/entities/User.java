package kg.manas.crm.entities;

import javax.persistence.*;

import kg.manas.crm.models.UserModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "id_generator", sequenceName = "users_seq", allocationSize = 1)
public class User extends BaseEntity {
    String username;

    public UserModel toModel() {
        return UserModel.builder()
                .id(id)
                .username(username)
                .build();
    }
}
