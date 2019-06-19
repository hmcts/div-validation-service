package uk.gov.hmcts.reform.divorce.validationservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.response.ValidationResponse;
import uk.gov.hmcts.reform.divorce.validationservice.service.ValidationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceControllerTest {

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ValidationServiceController validationServiceController;

    @Test
    public void givenCoreCaseData_whenValidateIsCalled_thenReturnValidationResult() {
        CoreCaseData coreCaseData = new CoreCaseData();
        ValidationRequest validationRequest = ValidationRequest.builder()
                .data(coreCaseData)
                .formId("formId")
                .section("all")
                .build();
        ValidationResponse validationResponse = ValidationResponse.builder().validationStatus("success").build();
        when(validationService.validate(validationRequest)).thenReturn(validationResponse);
        validationServiceController.validate(validationRequest);
        verify(validationService, times(1)).validate(any(ValidationRequest.class));
    }

}
