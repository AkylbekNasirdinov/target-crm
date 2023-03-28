package kg.manas.crm.services;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.entities.UserServices;
import kg.manas.crm.models.CustomerModel;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();

    CustomerModel findModelById(Long id);
    Customer findById(Long id);

    List<UserServices> findPurchasesByCategoryInMonth(Long customerId);
}
