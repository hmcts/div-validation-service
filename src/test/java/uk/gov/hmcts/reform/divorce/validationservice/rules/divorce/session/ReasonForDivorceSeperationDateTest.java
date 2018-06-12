package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReasonForDivorceSeperationDateTest {

    private ReasonForDivorceSeperationDate rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new ReasonForDivorceSeperationDate();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenFactIsSeperation2YearsAndReasonForDivorceSeperationDateIsNull() {
        divorceSession.setReasonForDivorce("separation-2-years");
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenFactIsNotSeperationAndReasonForDivorceSeperationDateIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(false, result);
    }

    @Test
    public void whenShouldReturnTrueWhenFactIsSeperation5YearsAndReasonForDivorceSeperationDateIsNull() {
        divorceSession.setReasonForDivorce("separation-5-years");
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenReasonForDivorceSeperationDateIsNotNull() {
        divorceSession.setReasonForDivorceSeperationDate(new Date());

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("reasonForDivorceSeperationDate can not be null or empty. Actual data is: null", rule.getResult());
    }
}