package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReasonForDivorceDesertionDateTest {

    private ReasonForDivorceDesertionDate rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new ReasonForDivorceDesertionDate();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenFactIsDesertionAndReasonForDivorceDesertionDateIsNull() {
        divorceSession.setReasonForDivorce("desertion");
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenFactIsNotDesertionAndReasonForDivorceDesertionDateIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(false, result);
    }

    @Test
    public void whenShouldReturnFalseWhenReasonForDivorceDesertionDateIsNotNull() {
        divorceSession.setReasonForDivorceDesertionDate(new Date());

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        divorceSession.setReasonForDivorce("desertion");
        rule.setDivorceSession(divorceSession);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("reasonForDivorceDesertionDate can not be null or empty. Actual data is: null",
            rule.getResult().get(0));
    }
}