package kg.manas.crm.proccessing.actions;

import kg.manas.crm.entities.ProcessStepParam;

import java.util.List;
import java.util.Map;

public interface Action {
    void execute(Map<String, Object> context, List<ProcessStepParam> params);
}
