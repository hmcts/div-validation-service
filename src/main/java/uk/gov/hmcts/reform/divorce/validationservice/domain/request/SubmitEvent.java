package uk.gov.hmcts.reform.divorce.validationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitEvent {
    @JsonProperty("id")
    private long caseId;
}
