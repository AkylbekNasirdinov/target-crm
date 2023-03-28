package kg.manas.crm.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParameterType {
    TEST("sad");


    @Getter
    private final String description;
}
