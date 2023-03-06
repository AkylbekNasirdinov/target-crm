package kg.manas.crm.service;

import kg.manas.crm.entity.UserServices;

import java.util.List;

public interface UserService {
    UserServices purchase(Long serviceId, Long customerId);
    List<UserServices> getAllPurchases();
}
