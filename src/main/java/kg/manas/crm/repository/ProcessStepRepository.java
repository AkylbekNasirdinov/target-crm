package kg.manas.crm.repository;

import kg.manas.crm.entities.ProcessStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessStepRepository extends JpaRepository<ProcessStep, Long> {
    Optional<ProcessStep> findByProcessIdAndIsInitialStepIsTrue(Long processId);
}
