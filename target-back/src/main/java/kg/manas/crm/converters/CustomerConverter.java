package kg.manas.crm.converters;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.models.CustomerModel;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends Converter<Customer, CustomerModel>{
    public CustomerConverter(Reflections reflections, ApplicationContext applicationContext) {
        super(Customer.class, CustomerModel.class, reflections, applicationContext);
    }
}
