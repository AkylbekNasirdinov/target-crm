package kg.manas.crm.service.impl;

import kg.manas.crm.entity.UserServices;
import kg.manas.crm.repository.ServiceRepository;
import kg.manas.crm.repository.UserRepository;
import kg.manas.crm.repository.UserServicesRepository;
import kg.manas.crm.service.CustomerService;
import kg.manas.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicesRepository userServicesRepository;
    private final CustomerService customerService;
    private final ServiceRepository serviceRepository;

    @Override
    public UserServices purchase(Long serviceId, Long customerId) {
        UserServices userService = new UserServices();
        userService.setUser(customerService.findById(customerId));
        userService.setPurchasedService(serviceRepository.findById(serviceId).orElseThrow());
        return userServicesRepository.save(userService);
    }

    @Override
    public List<UserServices> getAllPurchases() {
        return userServicesRepository.findAll();
    }
}
