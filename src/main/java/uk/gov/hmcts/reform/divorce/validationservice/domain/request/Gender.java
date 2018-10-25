package uk.gov.hmcts.reform.divorce.validationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    FEMALE("female"),
    MALE("male");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
