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
public class ScreenHasMarriageBrokenTest {

    private ScreenHasMarriageBroken rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new ScreenHasMarriageBroken();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenScreenHasMarriageBrokenIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenScreenHasMarriageBrokenIsNotNull() {
        divorceSession.setScreenHasMarriageBroken("Yes");

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("screenHasMarriageBroken can not be null or empty. Actual data is: null", rule.getResult());
    }
}