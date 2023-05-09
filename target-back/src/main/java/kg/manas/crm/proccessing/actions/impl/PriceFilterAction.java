package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.UserServices;
import kg.manas.crm.enums.ParameterType;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("priceFilter")
public class PriceFilterAction implements Action {
    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<List<UserServices>> partitionedUserServices = (List<List<UserServices>>)context.get("monthlyPurchases");
        if (!params.stream().allMatch(param -> param.getParameterType().equals(ParameterType.PRICE_MINIMUM) ||param.getParameterType().equals(ParameterType.SERVICE_AMOUNT)))
            throw new IllegalArgumentException("wrong parameter types provided for this type of action");
        BigDecimal priceLimit = ConversionUtils.getBigDecimal(params, ParameterType.PRICE_MINIMUM);
        partitionedUserServices = filterUserServicesBySingleService(partitionedUserServices, userService -> priceLimit.compareTo(userService.getPurchasedService().getPrice()) >= 0);
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
