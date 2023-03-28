package kg.manas.crm.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class CronUtils {

    private Date getNextRunDate(String cron, LocalDateTime lastExecutionDate) {
        Date lastExecution = lastExecutionDate == null ? new Date() : Date.from(lastExecutionDate.atZone(ZoneId.systemDefault()).toInstant());
        CronSequenceGenerator generator = new CronSequenceGenerator(cron);
        return generator.next(lastExecution);
    }

    public static boolean isNeedToRun(String cron, LocalDateTime lastExecutionDate) {
        Date lastExecution = lastExecutionDate == null ? new Date() : Date.from(lastExecutionDate.atZone(ZoneId.systemDefault()).toInstant());
        Date nextRunDate = getNextRunDate(cron, lastExecutionDate);
        return lastExecution.after(nextRunDate);
    }
}
