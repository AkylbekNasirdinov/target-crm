package kg.manas.crm.services;

import kg.manas.crm.models.ProcessStepParamModel;

import java.util.List;

public interface ProcessStepParamService {

    List<ProcessStepParamModel> getAllParamsByStepId(Long stepId);
    ProcessStepParamModel create (ProcessStepParamModel processStepParamModel);
}
