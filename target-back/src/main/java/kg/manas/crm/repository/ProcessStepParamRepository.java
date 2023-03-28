package kg.manas.crm.repository;

import kg.manas.crm.entities.ProcessStepParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessStepParamRepository extends JpaRepository<ProcessStepParam, Long> {
    List<ProcessStepParam> findAllByProcessStepId(Long processStepId);
}
