package kg.manas.crm.converters;

import kg.manas.crm.entities.ProcessStep;
import kg.manas.crm.models.ProcessStepModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class ProcessStepConverter extends Converter<ProcessStep, ProcessStepModel>{
    public ProcessStepConverter(Reflections reflections) {
        super(ProcessStep.class, ProcessStepModel.class, reflections);
    }
}
