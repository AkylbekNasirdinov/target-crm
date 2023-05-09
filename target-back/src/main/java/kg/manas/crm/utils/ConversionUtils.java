package kg.manas.crm.utils;

import kg.manas.crm.entities.ProcessStepParam;
import kg.manas.crm.enums.ParameterType;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@UtilityClass
public class ConversionUtils {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH24:mm:ss");
    public Integer getInteger(List<ProcessStepParam> params, ParameterType parameterType) {
        return Integer.valueOf(getValueFromParams(params, parameterType));
    }

    public Double getDouble(List<ProcessStepParam> params, ParameterType parameterType) {
        return Double.valueOf(getValueFromParams(params, parameterType));
    }

    public BigDecimal getBigDecimal(List<ProcessStepParam> params, ParameterType parameterType) {
        return BigDecimal.valueOf(getDouble(params, parameterType));
    }

    public Boolean getBoolean(List<ProcessStepParam> params, ParameterType parameterType) {
        return Boolean.valueOf(getValueFromParams(params, parameterType));
    }

    public LocalDateTime [] getDatePeriod (List<ProcessStepParam> params, ParameterType parameterType) {
        String [] datePeriod = getValueFromParams(params, parameterType).split(";");
        if (datePeriod.length < 2)
            throw new IllegalArgumentException("wrong date period provided");
        return new LocalDateTime[]{LocalDateTime.parse(datePeriod[0], dateTimeFormatter), LocalDateTime.parse(datePeriod[1], dateTimeFormatter)};
    }

    public String getValueFromParams(List<ProcessStepParam> params, ParameterType parameterType) {
        return params.stream()
                .filter(param -> param.getParameterType().equals(parameterType))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("no parameter of type {} was provided", parameterType.name())))
                .getValue();
    }
}
