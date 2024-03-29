package kg.manas.crm.repository;

import kg.manas.crm.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserServicesRepository extends JpaRepository<Purchase, Long> {
    @Query(value = "select * from USER_SERVICES as u where u.CUSTOMER_ID = ?1 " +
            "and u.CREATED_AT <  CURRENT_TIMESTAMP - interval '1 month'",nativeQuery = true)
    List<Purchase> findCustomerPurchasesInMonth(Long customerId);
}
