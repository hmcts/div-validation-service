package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class D8ScreenHasMarriageBrokenTest {

    private D8ScreenHasMarriageBroken rule;
    private CoreCaseData coreCaseData;

    @Before
    public void setup() {
        rule = new D8ScreenHasMarriageBroken();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8ScreenHasMarriageBrokenIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8ScreenHasMarriageBrokenIsNotNull() {
        coreCaseData.setD8ScreenHasMarriageBroken("Yes");

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8ScreenHasMarriageBroken can not be null or empty. Actual data is: null",
            rule.getResult().get(0));
    }
}