package uk.gov.hmcts.reform.divorce.validationservice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.response.ValidationResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    private CoreCaseData coreCaseData = new CoreCaseData();

    @Before
    public void setup() {
        assertNotNull(validationService);
        coreCaseData = new CoreCaseData();
        coreCaseData.setD8legalProcess("Divorce");
        coreCaseData.setD8MarriageDate("2007-12-12");
    }

    @Test
    public void givenCoreCaseData_whenInvalidD8LegalProcess_thenFailValidation() {
        coreCaseData.setD8legalProcess("notValid");
        assertEquals("expect to pass", "failed", validate().getValidationStatus());
    }

    @Test
    public void givenCoreCaseData_whenValidD8LegalProcess_thenValidationWillSucceed() {
        coreCaseData.setD8legalProcess("Divorce");
        assertEquals("expect to pass", "success", validate().getValidationStatus());
    }

    @Test
    public void givenCoreCaseData_whenValidD8MarriageDate_thenValidationWillSucceed() {
        coreCaseData.setD8MarriageDate("2007-12-12");
        assertEquals("expect to pass", "success", validate().getValidationStatus());
    }

    @Test
    public void givenCoreCaseData_whenInValidD8MarriageDate_thenFailValidation() {
        coreCaseData.setD8MarriageDate("1700-10-10");
        assertEquals("expect to pass", "failed", validate().getValidationStatus());
    }

    @Test
    public void givenCoreCaseData_whenD8MarriageDateInFuture_thenValidationWillFail() {
        coreCaseData.setD8MarriageDate("1700-10-10");
        assertEquals("expect to pass", "failed", validate().getValidationStatus());
    }

    public ValidationRequest toValiationRequest(CoreCaseData caseData) {
        return ValidationRequest.builder()
                .formId("formId")
                .data(caseData)
                .section("sec")
                .build();
    }

    private ValidationResponse validate() {
        return validationService.validate(toValiationRequest(coreCaseData));
    }


}
