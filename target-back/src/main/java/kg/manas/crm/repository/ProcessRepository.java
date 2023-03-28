package kg.manas.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kg.manas.crm.entities.Process;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findAllByIsActiveIsTrue();
}
