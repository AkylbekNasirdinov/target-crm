package kg.manas.crm.converters;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.models.ProcessStepParamModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class ProcessStepParamConverter extends Converter<ProcessStepParam, ProcessStepParamModel> {
    public ProcessStepParamConverter(Reflections reflections) {
        super(ProcessStepParam.class, ProcessStepParamModel.class, reflections);
    }
}
