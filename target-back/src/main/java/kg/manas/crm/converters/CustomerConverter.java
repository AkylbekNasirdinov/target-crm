package kg.manas.crm.converters;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.models.CustomerModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends Converter<Customer, CustomerModel>{
    public CustomerConverter(Reflections reflections) {
        super(Customer.class, CustomerModel.class, reflections);
    }
}
