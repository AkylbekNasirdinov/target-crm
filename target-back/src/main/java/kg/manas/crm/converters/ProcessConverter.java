package kg.manas.crm.converters;

import kg.manas.crm.entities.Process;
import kg.manas.crm.models.ProcessModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class ProcessConverter extends Converter<Process, ProcessModel>{
    public ProcessConverter(Reflections reflections) {
        super(Process.class, ProcessModel.class, reflections);
    }
}
