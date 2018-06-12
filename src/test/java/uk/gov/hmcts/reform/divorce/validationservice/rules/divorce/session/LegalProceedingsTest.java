package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LegalProceedingsTest {

    private LegalProceedings rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new LegalProceedings();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenLegalProceedingsIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenLegalProceedingsIsNotNull() {
        divorceSession.setLegalProceedings("Yes");

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("legalProceedings can not be null or empty. Actual data is: null", rule.getResult());
    }
}