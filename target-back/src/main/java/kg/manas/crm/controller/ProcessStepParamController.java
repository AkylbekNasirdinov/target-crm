package kg.manas.crm.controller;

import kg.manas.crm.models.ProcessStepParamModel;
import kg.manas.crm.services.ProcessStepParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/step/params")
public class ProcessStepParamController {

    private final ProcessStepParamService processStepParamService;

    @GetMapping
    List<ProcessStepParamModel> fetchParamsByStepId(@RequestParam Long id) {
        return processStepParamService.getAllParamsByStepId(id);
    }


    @PostMapping
    ProcessStepParamModel create(ProcessStepParamModel processStepParamModel) {
        return processStepParamService.create(processStepParamModel);
    }
}
