package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ProcessConverter;
import kg.manas.crm.models.ProcessModel;
import kg.manas.crm.entities.Process;
import kg.manas.crm.repository.ProcessRepository;
import kg.manas.crm.services.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {
    private final ProcessRepository processRepository;
    private final ProcessConverter processConverter;


    @Override
    public List<ProcessModel> getAllActive() {
        return processRepository.findAllByIsActiveIsTrue().stream().map(processConverter::convetToModel).collect(Collectors.toList());
    }

    @Override
    public ProcessModel create(ProcessModel processModel) {
        Process process = processRepository.save(processConverter.convertToEntity(processModel));
        return processConverter.convetToModel(process);
    }
}
