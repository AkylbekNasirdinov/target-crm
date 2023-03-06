package kg.manas.crm.service;

import kg.manas.crm.entity.Customer;
import kg.manas.crm.entity.ServiceCategory;
import kg.manas.crm.entity.UserServices;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();

    Customer findById(Long id);

    List<UserServices> findPurchasesByCategoryInMonth(Long customerId);
}
