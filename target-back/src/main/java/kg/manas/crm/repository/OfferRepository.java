package kg.manas.crm.repository;

import kg.manas.crm.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByCustomerIdAndProcessId(Long customerId, Long processId);
}
