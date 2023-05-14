package kg.manas.crm.services;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.models.CustomerModel;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();

    CustomerModel findModelById(Long id);
    Customer findById(Long id);

    List<Purchase> findPurchasesByCategoryInMonth(Long customerId);
}
