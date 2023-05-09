package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ProcessStepConverter;
import kg.manas.crm.entities.ProcessStep;
import kg.manas.crm.models.ProcessStepModel;
import kg.manas.crm.repository.ProcessStepRepository;
import kg.manas.crm.services.ProcessStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessStepServiceImpl implements ProcessStepService {
    private final ProcessStepRepository processStepRepository;
    private final ProcessStepConverter processStepConverter;
    @Override
    public List<ProcessStepModel> getAllByProcessId(Long processId) {
        return processStepRepository.findByProcessId(processId).stream().map(processStepConverter::convetToModel).collect(Collectors.toList());
    }

    @Override
    public ProcessStepModel create(ProcessStepModel processStepModel) {
        ProcessStep processStep = processStepRepository.save(processStepConverter.convertToEntity(processStepModel));
        return processStepConverter.convetToModel(processStep);
    }
}
