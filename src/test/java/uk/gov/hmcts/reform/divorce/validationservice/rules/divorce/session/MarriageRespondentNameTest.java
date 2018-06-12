package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarriageRespondentNameTest {

    private MarriageRespondentName rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new MarriageRespondentName();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenMarriageRespondentNameIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenMarriageRespondentNameIsNotNull() {
        divorceSession.setMarriageRespondentName("Yes");

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("marriageRespondentName can not be null or empty. Actual data is: null", rule.getResult());
    }
}