package kg.manas.crm.services;

import kg.manas.crm.models.ProcessModel;

import java.util.List;

public interface ProcessService {
    List<ProcessModel> getAllActive();
    ProcessModel create (ProcessModel processModel);
}
