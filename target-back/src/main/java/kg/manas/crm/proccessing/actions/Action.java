package kg.manas.crm.proccessing.actions;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Purchase;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Action {
    void execute(Map<String, Object> context, List<ProcessStepParam> params);
    default List<List<Purchase>> filterUserServicesBySingleService(List<List<Purchase>> initialUserServices, Function<Purchase, Boolean> predicateFunction) {
        return initialUserServices.stream().map(userServices -> userServices.stream()
                        .filter(predicateFunction::apply).collect(Collectors.toList()))
                .filter(userServices -> !userServices.isEmpty()).collect(Collectors.toList());
    }
    default List<List<Purchase>> filterUserServices(List<List<Purchase>> initialUserServices, Function<List<Purchase>, Boolean> predicateFunction) {
        return initialUserServices.stream().filter(predicateFunction::apply).collect(Collectors.toList());
    }
}
