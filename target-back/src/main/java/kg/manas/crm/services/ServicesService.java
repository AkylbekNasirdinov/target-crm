package kg.manas.crm.services;

import kg.manas.crm.models.ServiceModel;

import java.util.List;

public interface ServicesService {
    ServiceModel findById(Long id);
    List<ServiceModel> findAll();
}
