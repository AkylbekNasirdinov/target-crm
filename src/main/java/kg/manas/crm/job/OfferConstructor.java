package kg.manas.crm.job;

import kg.manas.crm.entity.*;
import kg.manas.crm.repository.OfferRepository;
import kg.manas.crm.repository.ServiceCategoryRepository;
import kg.manas.crm.repository.ServiceRepository;
import kg.manas.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "job.offer-constructor",
        name = "enabled",
        havingValue = "true"
)
public class OfferConstructor {

    private final CustomerService customerService;
    private final ServiceRepository serviceRepository;
    private final OfferRepository offerRepository;

    private final ServiceCategoryRepository serviceCategoryRepository;
    @Scheduled(cron = "${job.offer-constructor.cron}")
    public void constructOffers() {
        log.info("Offer construction started");
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(customer -> {
            List<UserServices> purchases = customerService.findPurchasesByCategoryInMonth(customer.getId());
            categories.forEach(category -> {
                List<UserServices> purchasesByCategory = purchases.stream().filter(purchase -> Objects.equals(purchase.getPurchasedService().getCategory(), category)).collect(Collectors.toList());
                if (purchasesByCategory.size()  >= 3){
                    Offer offer = new Offer();
                    offer.setCustomer(customer);
                    Service offeredService = serviceRepository.findAllByCategoryId(category.getId()).stream()
                            .filter(service -> service.getPrice().compareTo(purchasesByCategory.get(0).getPurchasedService().getPrice()) > 0).findFirst().orElse(new Service());
                    if (offeredService.getId() == null)
                        return;
                    offer.setService(offeredService);
                    offerRepository.save(offer);
                    log.info("Offer {} constructed", offer);
                }
            });

        });
    }

}
