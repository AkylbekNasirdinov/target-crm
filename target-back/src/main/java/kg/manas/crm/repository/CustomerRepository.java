package kg.manas.crm.repository;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
