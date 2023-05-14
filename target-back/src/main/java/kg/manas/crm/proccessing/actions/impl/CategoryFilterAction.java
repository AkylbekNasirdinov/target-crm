package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.enums.ParameterType;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("categoryFilter")
public class CategoryFilterAction implements Action {

    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<List<Purchase>> partitionedUserServices = (List<List<Purchase>>)context.get("monthlyPurchases");
        String serviceCategory = ConversionUtils.getValueFromParams(params, ParameterType.CATEGORY);
        partitionedUserServices = filterUserServicesBySingleService(partitionedUserServices, userService -> userService.getPurchasedService().getCategory().getName().equals(serviceCategory));
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
