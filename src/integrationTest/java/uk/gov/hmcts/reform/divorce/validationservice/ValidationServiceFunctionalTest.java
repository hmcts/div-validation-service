package uk.gov.hmcts.reform.divorce.validationservice;

import io.restassured.response.Response;
import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringIntegrationSerenityRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ValidationServiceFunctionalTest {

    @Value("${validation-service.base-url}")
    private String validationServiceBaseUrl;

    @Value("${validation-service.validate-endpoint}")
    private String validationServiceValidateEndpoint;

    @Test
    public void givenNoFormId_whenValidateEndpointIsCalled_thenReturnSuccess() throws Exception {
        Response response = postToValidateEndpoint("request/valid-unused-form-id-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/success-response.json"), response.prettyPrint());
    }

    @Test
    public void givenValidCaseFormData_whenValidateEndpointIsCalled_thenReturnSuccess() throws Exception {
        Response response = postToValidateEndpoint("request/valid-d8-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/success-response.json"), response.prettyPrint());
    }

    @Test
    public void givenValidSessionFormData_whenValidateEndpointIsCalled_thenReturnSuccess() throws Exception {
        Response response = postToValidateEndpoint("request/valid-divorce-session-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/success-response.json"), response.prettyPrint());
    }

    @Test
    public void givenInvalidCaseFormData_whenValidateEndpointIsCalled_thenReturnFailure() throws Exception {
        Response response = postToValidateEndpoint("request/invalid-d8-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/failure-d8-response.json"), response.prettyPrint());
    }

    @Test
    public void givenInvalidSessionFormData_whenValidateEndpointIsCalled_thenReturnFailure() throws Exception {
        Response response = postToValidateEndpoint("request/invalid-divorce-session-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/failure-divorce-session-response.json"),
            response.prettyPrint());
    }

    @Test
    public void givenMarriageDateMoreThan100YearsAgoCaseFormData_whenValidateEndpointIsCalled_thenReturnFailure() throws Exception {
        Response response = postToValidateEndpoint("request/invalid-d8-marriage-date-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/failure-d8-marriage-date-response.json"),
            response.prettyPrint());
    }

    @Test
    public void givenMarriageDateMoreThan100YearsAgoSessionFormData_whenValidateEndpointIsCalled_thenReturnFailure() throws Exception {
        Response response = postToValidateEndpoint("request/invalid-divorce-session-marriage-date-request.json");
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assert.assertEquals(getJsonFileAsString("response/failure-divorce-session-marriage-date-response.json"),
            response.prettyPrint());
    }

    private Response postToValidateEndpoint(String filePath) throws Exception {
        SerenityRest.useRelaxedHTTPSValidation();
        return SerenityRest.given()
                .contentType("application/json")
                .body(getJsonFileAsString(filePath))
                .post(validationServiceBaseUrl.concat(validationServiceValidateEndpoint))
                .andReturn();
    }

    private String getJsonFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(
            Paths.get("src", "integrationTest", "resources", "validationservice", filePath)
        ));
    }
}
