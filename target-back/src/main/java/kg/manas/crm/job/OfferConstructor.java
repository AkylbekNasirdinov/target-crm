package kg.manas.crm.job;

import kg.manas.crm.entities.Process;
import kg.manas.crm.proccessing.executor.ProcessExecutor;
import kg.manas.crm.repository.ProcessRepository;
import kg.manas.crm.utils.CronUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private final ApplicationContext applicationContext;
    private ThreadPoolTaskExecutor taskExecutor;
    @Scheduled(cron = "${job.offer-constructor.cron}")
    public void constructOffers() {
        List<Process> processes = processRepository.findAllByIsActiveIsTrue();
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(0);
        taskExecutor.setMaxPoolSize(processes.size());
        taskExecutor.initialize();
        log.info("Offer construction started");

        processes.forEach(process -> taskExecutor.execute(applicationContext.getBean(ProcessExecutor.class, process)));
    }

}
