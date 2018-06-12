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
public class PetitionerLastNameTest {

    private PetitionerLastName rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new PetitionerLastName();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenPetitionerLastNameIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenPetitionerLastNameIsNotNull() {
        divorceSession.setPetitionerLastName("Yes");

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("petitionerLastName can not be null or empty. Actual data is: null", rule.getResult());
    }
}