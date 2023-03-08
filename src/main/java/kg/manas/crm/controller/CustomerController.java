package kg.manas.crm.controller;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.models.CustomerModel;
import kg.manas.crm.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get-all")
    public List<Customer> getAll() {
       return customerService.getAllCustomers();
    }
    @GetMapping()
    public CustomerModel fetchById(@RequestParam Long id) {
        return customerService.findModelById(id);
    }


    @PostMapping("/save")
    public Customer save(Customer customer) {
       return customerService.save(customer);
    }
}
