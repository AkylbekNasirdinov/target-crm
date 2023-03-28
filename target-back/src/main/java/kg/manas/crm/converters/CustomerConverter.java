package kg.manas.crm.converters;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.models.CustomerModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerConverter extends Converter<Customer, CustomerModel>{
    public CustomerConverter() {
        super(Customer.class, CustomerModel.class);
    }
}
