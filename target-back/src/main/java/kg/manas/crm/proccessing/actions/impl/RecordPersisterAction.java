package kg.manas.crm.proccessing.actions.impl;

import kg.manas.crm.entities.Offer;
import kg.manas.crm.entities.Process;
import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.entities.Purchase;
import kg.manas.crm.proccessing.actions.Action;
import kg.manas.crm.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("recordPersister")
@RequiredArgsConstructor
public class RecordPersisterAction implements Action {
    private final OfferRepository offerRepository;
    @Override
    public void execute(Map<String, Object> context, List<ProcessStepParam> params) {
        List<List<Purchase>> partitionedUserServices = (List<List<Purchase>>)context.get("monthlyPurchases");
        Process process = ((Process)context.get("process"));
        partitionedUserServices.forEach(purchases -> {
            Offer offer = Offer.builder()
                    .service(process.getOfferedService())
                    .customer(purchases.get(0).getCustomer())
                    .isConfirmed(false)
                    .process(process)
                    .build();
            offerRepository.save(offer);
        });
    }
}
