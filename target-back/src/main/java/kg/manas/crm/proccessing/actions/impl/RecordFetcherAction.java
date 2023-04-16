package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.User;
import kg.manas.crm.entities.UserServices;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.repository.UserRepository;
import kg.manas.crm.repository.UserServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("recordFetcher")
@RequiredArgsConstructor
public class RecordFetcherAction implements Action {

    private final UserServicesRepository userServicesRepository;
    private final UserRepository userRepository;

    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<User> users = userRepository.findAll();
        List<List<UserServices>> partitionedUserServices = users.stream()
                .map(user -> userServicesRepository.findCustomerPurchasesInMonth(user.getId())).collect(Collectors.toList());
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
