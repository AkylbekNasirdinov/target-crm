package kg.manas.crm.services;

import kg.manas.crm.entities.UserServices;
import kg.manas.crm.models.UserServicesModel;

import java.util.List;

public interface UserService {
    UserServicesModel purchase(Long serviceId, Long customerId);
    List<UserServicesModel> getAllPurchases();
}
