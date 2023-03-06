package kg.manas.crm.service.impl;

import kg.manas.crm.entity.Customer;
import kg.manas.crm.entity.UserServices;
import kg.manas.crm.repository.CustomerRepository;
import kg.manas.crm.repository.UserServicesRepository;
import kg.manas.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserServicesRepository userServicesRepository;

    private final CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<UserServices> findPurchasesByCategoryInMonth(Long customerId) {
        return userServicesRepository.findCustomerPurchasesInMonth(customerId);
    }
}
