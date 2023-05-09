package kg.manas.crm.controller;

import kg.manas.crm.models.ProcessStepModel;
import kg.manas.crm.services.ProcessStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class ProcessStepController {
    private final ProcessStepService processStepService;

    @GetMapping
    public List<ProcessStepModel> fetchStepsByProcessId(@RequestParam Long processId) {
        return processStepService.getAllByProcessId(processId);
    }

    @PostMapping("/create")
    public ProcessStepModel create(ProcessStepModel processStepModel) {
        return processStepService.create(processStepModel);
    }
}
