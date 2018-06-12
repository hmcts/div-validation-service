package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class D8ConnectionsTest {

    private D8Connections rule;
    private CoreCaseData coreCaseData;
    private uk.gov.hmcts.reform.divorce.validationservice.domain.request.Connections D8Connections;

    @Before
    public void setup() {
        rule = new D8Connections();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8ConnectionsIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8ConnectionsIsNotNull() {
        D8Connections = new uk.gov.hmcts.reform.divorce.validationservice.domain.request.Connections();
        coreCaseData.setD8Connections(D8Connections);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.then();

        assertEquals("D8Connections can not be null or empty. Actual data is: null", rule.getResult());
    }
}