package kg.manas.crm.job;

import kg.manas.crm.entities.Process;
import kg.manas.crm.proccessing.executor.ProcessExecutor;
import kg.manas.crm.repository.ProcessRepository;
import kg.manas.crm.utils.CronUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "job.offer-constructor",
        name = "enabled",
        havingValue = "true"
)
public class OfferConstructor {
    private final ProcessRepository processRepository;
    private TaskExecutor taskExecutor;
    @Scheduled(cron = "${job.offer-constructor.cron}")
    public void constructOffers() {
        taskExecutor = new ThreadPoolTaskExecutor();
        log.info("Offer construction started");
        List<Process> processes = processRepository.findAllByIsActiveIsTrue().stream()
                .filter(process -> CronUtils.isNeedToRun(process.getExecutionCron(), process.getLastExecutionDate()))
                .toList();
        processes.forEach(process -> taskExecutor.execute(new ProcessExecutor(process)));
//        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
//        List<Customer> customers = customerService.getAllCustomers();
//        customers.forEach(customer -> {
//            List<UserServices> purchases = customerService.findPurchasesByCategoryInMonth(customer.getId());
//            categories.forEach(category -> {
//                List<UserServices> purchasesByCategory = purchases.stream().filter(purchase -> Objects.equals(purchase.getPurchasedService().getCategory(), category)).collect(Collectors.toList());
//                if (purchasesByCategory.size()  >= 3){
//                    Offer offer = new Offer();
//                    offer.setCustomer(customer);
//                    Service offeredService = serviceRepository.findAllByCategoryId(category.getId()).stream()
//                            .filter(service -> service.getPrice().compareTo(purchasesByCategory.get(0).getPurchasedService().getPrice()) > 0).findFirst().orElse(new Service());
//                    if (offeredService.getId() == null)
//                        return;
//                    offer.setService(offeredService);
//                    offerRepository.save(offer);
//                    log.info("Offer {} constructed", offer);
//                }
//            });
//
//        });
    }

}
