package kg.manas.crm.controller;

import kg.manas.crm.models.ParameterRequestModel;
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
    ProcessStepParamModel fetchParamsByStepId(@RequestParam Long id) {
        return processStepParamService.getAllParamsByStepId(id);
    }


    @PostMapping
    ProcessStepParamModel create(ParameterRequestModel processStepParamModel) {
        return processStepParamService.save(processStepParamModel);
    }
}
