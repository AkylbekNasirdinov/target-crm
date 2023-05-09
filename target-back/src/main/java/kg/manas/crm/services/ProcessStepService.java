package kg.manas.crm.services;

import kg.manas.crm.models.ProcessStepModel;

import java.util.List;

public interface ProcessStepService {
    List<ProcessStepModel> getAllByProcessId(Long processId);
    ProcessStepModel create (ProcessStepModel processStepModel);
}
