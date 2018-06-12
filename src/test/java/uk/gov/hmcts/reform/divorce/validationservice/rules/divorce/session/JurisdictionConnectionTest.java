package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.ArrayList;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JurisdictionConnectionTest {

    private JurisdictionConnection rule;
    private DivorceSession divorceSession;

    @Before
    public void setup() {
        rule = new JurisdictionConnection();
        divorceSession = new DivorceSession();
    }

    @Test
    public void whenShouldReturnTrueWhenJurisdictionConnectionIsNull() {
        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenJurisdictionConnectionIsNotNull() {
        List<String> jurisdictionConnection = new ArrayList<>();
        divorceSession.setJurisdictionConnection(jurisdictionConnection);

        rule.setDivorceSession(divorceSession);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setDivorceSession(divorceSession);

        rule.then();

        assertEquals("jurisdictionConnection can not be null or empty. Actual data is: null", rule.getResult());
    }
}