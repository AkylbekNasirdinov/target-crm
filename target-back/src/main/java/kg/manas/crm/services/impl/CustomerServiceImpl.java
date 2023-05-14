package kg.manas.crm.services.impl;

import kg.manas.crm.converters.CustomerConverter;
import kg.manas.crm.entities.Customer;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.models.CustomerModel;
import kg.manas.crm.repository.CustomerRepository;
import kg.manas.crm.repository.UserServicesRepository;
import kg.manas.crm.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserServicesRepository userServicesRepository;

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerModel findModelById(Long id) {
        return customerConverter.convetToModel(customerRepository.findById(id).orElseThrow());
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Purchase> findPurchasesByCategoryInMonth(Long customerId) {
        return userServicesRepository.findCustomerPurchasesInMonth(customerId);
    }
}
