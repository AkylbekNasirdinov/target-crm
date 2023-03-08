package kg.manas.crm.services.impl;

import kg.manas.crm.converters.Converter;
import kg.manas.crm.converters.UserConverter;
import kg.manas.crm.converters.UserServicesConverter;
import kg.manas.crm.entities.UserServices;
import kg.manas.crm.models.UserServicesModel;
import kg.manas.crm.repository.ServiceRepository;
import kg.manas.crm.repository.UserServicesRepository;
import kg.manas.crm.services.CustomerService;
import kg.manas.crm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicesRepository userServicesRepository;
    private final CustomerService customerService;
    private final ServiceRepository serviceRepository;
    private final UserServicesConverter userServicesConverter;

    @Override
    public UserServicesModel purchase(Long serviceId, Long customerId) {
        UserServices userService = new UserServices();
        userService.setUser(customerService.findById(customerId));
        userService.setPurchasedService(serviceRepository.findById(serviceId).orElseThrow());
        return userServicesConverter.convetToModel(userServicesRepository.save(userService));
    }

    @Override
    public List<UserServicesModel> getAllPurchases() {
        return userServicesRepository.findAll().stream().map(userServicesConverter::convetToModel).collect(Collectors.toList());
    }
}
