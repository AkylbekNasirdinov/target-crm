package kg.manas.crm.proccessing.executor;

import kg.manas.crm.entities.Process;
import kg.manas.crm.entities.ProcessStep;
import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.repository.ProcessStepParamRepository;
import kg.manas.crm.repository.ProcessStepRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection"})
public class ProcessExecutor implements Runnable{
    private final Map<String, Object> processContext;
    @Getter
    private final Process process;
    @Autowired
    private ProcessStepRepository processStepRepository;
    @Autowired
    private ProcessStepParamRepository processStepParamRepository;
    @Autowired
    private ApplicationContext applicationContext;

    public ProcessExecutor(@NotNull Process process) {
        this.process = process;
        this.processContext = new HashMap<>();
        this.processContext.put("process", process);
    }

    @Override
    public void run() {
        ProcessStep currentStep = processStepRepository.findByProcessIdAndIsInitialStepIsTrue(process.getId())
                .orElseThrow();
        while (true) {
            Action action = applicationContext.getBean(currentStep.getActionDefinition().getActionQualifier(), Action.class);
            List<ProcessStepParam> stepParams = processStepParamRepository.findAllByProcessStepId(currentStep.getId());
            action.execute(processContext, stepParams);
            currentStep = currentStep.getNextStep();
            if (currentStep == null)
                break;
        }

    }

}
