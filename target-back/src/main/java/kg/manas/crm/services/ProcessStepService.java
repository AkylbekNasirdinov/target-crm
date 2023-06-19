package kg.manas.crm.services;

import kg.manas.crm.entities.ProcessStep;
import kg.manas.crm.models.ProcessStepModel;

import java.util.List;

public interface ProcessStepService {
    List<ProcessStep> getAllByProcessId(Long processId);
    ProcessStep create (ProcessStepModel processStepModel);
}
