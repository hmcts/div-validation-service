package uk.gov.hmcts.reform.divorce.emclient;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Lazy
@RunWith(SerenityParameterizedRunner.class)
@PropertySource("classpath:application.properties")
public class ValidationServiceIntegrationTest {

    @Rule
    public SpringIntegrationMethodRule springMethodIntegration = new SpringIntegrationMethodRule();

    @Value("${validation-service.base-url}")
    private String validationServiceBaseUrl;

    @Value("${validation-service.validate-endpoint}")
    private String validationServiceValidateEndpoint;

    @Test
    private void givenValidCaseFormData_whenValidateEndpointIsCalled_thenReturnSuccess() {

    }
}
