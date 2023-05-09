package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ProcessStepParamConverter;
import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.models.ProcessStepParamModel;
import kg.manas.crm.repository.ProcessStepParamRepository;
import kg.manas.crm.services.ProcessStepParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessStepParamServiceImpl implements ProcessStepParamService {
    private final ProcessStepParamRepository processStepParamRepository;
    private final ProcessStepParamConverter processStepParamConverter;
    @Override
    public List<ProcessStepParamModel> getAllParamsByStepId(Long stepId) {
        return processStepParamRepository.findAllByProcessStepId(stepId).stream().map(processStepParamConverter::convetToModel).collect(Collectors.toList());
    }

    @Override
    public ProcessStepParamModel create(ProcessStepParamModel processStepParamModel) {
        ProcessStepParam processStepParam = processStepParamRepository.save(processStepParamConverter.convertToEntity(processStepParamModel));
        return processStepParamConverter.convetToModel(processStepParam);
    }
}
