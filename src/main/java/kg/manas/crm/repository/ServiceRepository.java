package kg.manas.crm.repository;

import kg.manas.crm.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByCategoryId(Long id);
}
