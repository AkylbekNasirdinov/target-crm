package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.Customer;
import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Process;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.repository.CustomerRepository;
import kg.manas.crm.repository.OfferRepository;
import kg.manas.crm.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("recordFetcher")
@RequiredArgsConstructor
public class RecordFetcherAction implements Action {

    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;
    private final OfferRepository offerRepository;

    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<Customer> customers = customerRepository.findAll();
        customers = customers.stream().filter(customer -> offerRepository.findByCustomerIdAndProcessId(customer.getId(), ((Process)context.get("process")).getId()).isEmpty()).collect(Collectors.toList());
        List<List<Purchase>> partitionedUserServices = customers.stream()
                .map(customer -> purchaseRepository.findAllByCustomerIdAndCreatedAtAfter(customer.getId(), LocalDateTime.now().minusMonths(1))).collect(Collectors.toList());
        context.put("monthlyPurchases", partitionedUserServices);
    }
}
