package kg.manas.crm.controller;

import kg.manas.crm.models.ServiceModel;
import kg.manas.crm.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    
    private final ServicesService servicesService;
    @GetMapping
    public ServiceModel fetchById(@RequestParam Long id) {
        return servicesService.findById(id);
    }
}
