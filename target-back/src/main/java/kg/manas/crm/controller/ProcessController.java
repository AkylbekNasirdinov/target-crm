package kg.manas.crm.controller;

import kg.manas.crm.models.ProcessModel;
import kg.manas.crm.services.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/processes")
public class ProcessController {
    private final ProcessService processService;


    @GetMapping("/all")
    public List<ProcessModel> fetchAllProcesses() {
        return processService.getAllActive();
    }

    @PostMapping
    public ProcessModel create(@RequestBody ProcessModel processModel) {
        return processService.create(processModel);

    }
}
