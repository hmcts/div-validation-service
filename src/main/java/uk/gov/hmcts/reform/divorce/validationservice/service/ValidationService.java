package uk.gov.hmcts.reform.divorce.validationservice.service;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.response.ValidationResponse;

public interface ValidationService {

    ValidationResponse validate(ValidationRequest validationRequest);

}
