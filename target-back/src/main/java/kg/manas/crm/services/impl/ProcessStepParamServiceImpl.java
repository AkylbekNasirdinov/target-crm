package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ProcessStepParamConverter;
import kg.manas.crm.entities.ProcessStep;
import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.models.ParameterRequestModel;
import kg.manas.crm.models.ProcessStepParamModel;
import kg.manas.crm.repository.ProcessStepParamRepository;
import kg.manas.crm.repository.ProcessStepRepository;
import kg.manas.crm.services.ProcessStepParamService;
import kg.manas.crm.services.ProcessStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessStepParamServiceImpl implements ProcessStepParamService {
    private final ProcessStepParamRepository processStepParamRepository;
    private final ProcessStepRepository processStepRepository;
    private final ProcessStepParamConverter processStepParamConverter;
    @Override
    public ProcessStepParamModel getAllParamsByStepId(Long stepId) {
        return processStepParamRepository.findAllByProcessStepId(stepId).stream().map(processStepParamConverter::convetToModel).findFirst().orElse(null);
    }

    @Override
    public ProcessStepParamModel save(ParameterRequestModel processStepParamModel) {
        ProcessStepParam processStepParam = processStepParamModel.getId() == null ? new ProcessStepParam() : processStepParamRepository.findById(processStepParamModel.getId()).orElseThrow();
        if (processStepParamModel.getId() == null) {
            processStepParam.setParameterType(processStepParamModel.getParameterType());
            ProcessStep step = processStepRepository.findById(processStepParamModel.getStepId()).orElseThrow();
            processStepParam.setProcessStep(step);
        }
        processStepParam.setValue(processStepParamModel.getValue());
        return processStepParamConverter.convetToModel(processStepParamRepository.save(processStepParam));
    }
}
