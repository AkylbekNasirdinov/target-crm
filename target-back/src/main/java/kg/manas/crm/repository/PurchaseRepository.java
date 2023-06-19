package kg.manas.crm.repository;

import kg.manas.crm.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByCustomerIdAndCreatedAtAfter(Long customerId, LocalDateTime dateTime);
}
