package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.UserServices;
import kg.manas.crm.enums.ParameterType;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service("dateFilter")
public class DateFilterAction implements Action {


    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        if (!params.stream().allMatch(param -> param.getParameterType().equals(ParameterType.DATE_PERIOD)))
            throw new IllegalArgumentException("wrong parameter types provided for this type of action");
        LocalDateTime [] datePeriod = ConversionUtils.getDatePeriod(params, ParameterType.DATE_PERIOD);
        LocalDateTime startDate = datePeriod[0];
        LocalDateTime endDate = datePeriod[1];
        List<List<UserServices>> partitionedUserServices = (List<List<UserServices>>) context.get("monthlyPurchases");
        partitionedUserServices = filterUserServicesBySingleService(partitionedUserServices, userService -> userService.getCreatedAt().isAfter(startDate)
                && userService.getCreatedAt().isBefore(endDate));
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
