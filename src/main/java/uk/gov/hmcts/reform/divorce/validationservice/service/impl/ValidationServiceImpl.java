package uk.gov.hmcts.reform.divorce.validationservice.service.impl;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.response.ValidationResponse;
import uk.gov.hmcts.reform.divorce.validationservice.service.ValidationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    @Qualifier("javaD8RuleBook")
    private RuleBook<List<String>> d8RuleBook;

    @Override
    public ValidationResponse validate(final ValidationRequest validationRequest) {
        NameValueReferableMap<CoreCaseData> facts = new FactMap<>();
        facts.setValue("coreCaseData", validationRequest.getData());
        ValidationResponse validationResponse = ValidationResponse.builder()
                .warnings(Collections.emptyList())
                .validationStatus("success")
                .build();

        d8RuleBook.run(facts);
        d8RuleBook.getResult().map(Result::getValue).ifPresent(result -> errorResponse(validationResponse, result));
        return validationResponse;
    }

    private void errorResponse(ValidationResponse validationResponse, List<String> result) {
        if (!result.isEmpty()) {
            validationResponse.setErrors(result);
            validationResponse.setValidationStatus("failed");
        }
    }

}