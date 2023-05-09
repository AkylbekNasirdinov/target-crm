package kg.manas.crm.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParameterType {
    DATE_PERIOD("filtering period"),
    PRICE_MINIMUM("single service price filter"),
    SERVICE_AMOUNT("service amount"),
    CATEGORY("service category"),
    PRICE_TOTAL("service total price filter");

    @Getter
    private final String description;
}
