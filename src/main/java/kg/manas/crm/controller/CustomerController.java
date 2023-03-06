package kg.manas.crm.controller;

import kg.manas.crm.entity.Customer;
import kg.manas.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/save")
    public Customer save(Customer customer) {
       return customerService.save(customer);
    }
}
