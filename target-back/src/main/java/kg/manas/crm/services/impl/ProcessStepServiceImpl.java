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
    public List<ProcessStep> getAllByProcessId(Long processId) {
        return processStepRepository.findAllByProcessId(processId);
    }

    @Override
    public ProcessStep create(ProcessStepModel processStepModel) {
        ProcessStep processStep = processStepRepository.save(processStepConverter.convertToEntity(processStepModel));
        return processStep;
    }
}
