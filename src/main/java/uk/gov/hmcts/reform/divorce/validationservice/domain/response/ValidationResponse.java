package uk.gov.hmcts.reform.divorce.validationservice.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationResponse {

    private String validationStatus;

    private List<String> errors;

    private List<String> warnings;
}