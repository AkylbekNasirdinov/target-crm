package kg.manas.crm.proccessing.actions;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.UserServices;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Action {
    void execute(Map<String, Object> context, List<ProcessStepParam> params);
    default List<List<UserServices>> filterUserServicesBySingleService(List<List<UserServices>> initialUserServices, Function<UserServices, Boolean> predicateFunction) {
        return initialUserServices.stream().map(userServices -> userServices.stream()
                        .filter(predicateFunction::apply).collect(Collectors.toList()))
                .filter(userServices -> !userServices.isEmpty()).collect(Collectors.toList());
    }
    default List<List<UserServices>> filterUserServices(List<List<UserServices>> initialUserServices, Function<List<UserServices>, Boolean> predicateFunction) {
        return initialUserServices.stream().filter(predicateFunction::apply).collect(Collectors.toList());
    }
}
