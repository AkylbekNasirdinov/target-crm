package kg.manas.crm.services.impl;

import kg.manas.crm.converters.PurchaseConverter;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.models.PurchaseModel;
import kg.manas.crm.repository.ServiceRepository;
import kg.manas.crm.repository.UserServicesRepository;
import kg.manas.crm.services.CustomerService;
import kg.manas.crm.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final UserServicesRepository userServicesRepository;
    private final CustomerService customerService;
    private final ServiceRepository serviceRepository;
    private final PurchaseConverter purchaseConverter;

    @Override
    public PurchaseModel purchase(Long serviceId, Long customerId) {
        Purchase userService = new Purchase();
        userService.setCustomer(customerService.findById(customerId));
        userService.setPurchasedService(serviceRepository.findById(serviceId).orElseThrow());
        return purchaseConverter.convetToModel(userServicesRepository.save(userService));
    }

    @Override
    public List<PurchaseModel> getAllPurchases() {
        return userServicesRepository.findAll().stream().map(purchaseConverter::convetToModel).collect(Collectors.toList());
    }
}
