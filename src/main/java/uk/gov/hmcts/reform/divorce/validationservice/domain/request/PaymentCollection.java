package uk.gov.hmcts.reform.divorce.validationservice.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentCollection {

    private String id;

    private Payment value;
}