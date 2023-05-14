package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ServiceConverter;
import kg.manas.crm.models.ServiceModel;
import kg.manas.crm.repository.ServiceRepository;
import kg.manas.crm.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServiceRepository serviceRepository;
    private final ServiceConverter serviceConverter;


    @Override
    public ServiceModel findById(Long id) {
        return serviceConverter.convetToModel(serviceRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ServiceModel> findAll() {
        return serviceRepository.findAll().stream().map(serviceConverter::convetToModel).collect(Collectors.toList());
    }
}
