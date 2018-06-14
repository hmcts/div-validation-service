package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class D8DivorceCostsClaimTest {

    private D8DivorceCostsClaim rule;
    private CoreCaseData divorceSession;

    @Before
    public void setup() {
        rule = new D8DivorceCostsClaim();
        divorceSession = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8DivorceCostsClaimIsNull() {
        rule.setCoreCaseData(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8DivorceCostsClaimIsNotNull() {
        divorceSession.setD8DivorceCostsClaim("Yes");

        rule.setCoreCaseData(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(divorceSession);

        rule.setResult(new ArrayList<>());
		rule.then();

        assertEquals("D8DivorceCostsClaim can not be null or empty. Actual data is: null", rule.getResult().get(0));
    }
}