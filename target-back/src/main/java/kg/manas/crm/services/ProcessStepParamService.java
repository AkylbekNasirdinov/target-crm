package kg.manas.crm.services;

import kg.manas.crm.models.ParameterRequestModel;
import kg.manas.crm.models.ProcessStepParamModel;

import java.util.List;

public interface ProcessStepParamService {

   ProcessStepParamModel getAllParamsByStepId(Long stepId);
    ProcessStepParamModel save(ParameterRequestModel processStepParamModel);
}
