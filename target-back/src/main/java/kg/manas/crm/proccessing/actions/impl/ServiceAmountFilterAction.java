package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.enums.ParameterType;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("serviceAmountFilter")
public class ServiceAmountFilterAction implements Action {

    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<List<Purchase>> partitionedUserServices = (List<List<Purchase>>)context.get("monthlyPurchases");
        Integer amount = ConversionUtils.getInteger(params, ParameterType.SERVICE_AMOUNT);
        partitionedUserServices = filterUserServices(partitionedUserServices, userServices -> userServices.size() >= amount);
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
