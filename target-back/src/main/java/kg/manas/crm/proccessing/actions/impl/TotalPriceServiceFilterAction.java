package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.enums.ParameterType;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("totalPriceFilter")
public class TotalPriceServiceFilterAction implements Action {
    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<List<Purchase>> partitionedUserServices = (List<List<Purchase>>)context.get("monthlyPurchases");
        BigDecimal totalPriceLimit = ConversionUtils.getBigDecimal(params, ParameterType.PRICE_TOTAL);
        partitionedUserServices = filterUserServices(partitionedUserServices, userServices -> {
           BigDecimal sumOfPurchases = userServices.stream().map(userService -> userService.getPurchasedService().getPrice())
                   .reduce(BigDecimal.ZERO, BigDecimal::add);
           return sumOfPurchases.compareTo(totalPriceLimit) >= 0;
        });
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
