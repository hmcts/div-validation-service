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
public class D8RespondentLastNameTest {

    private D8RespondentLastName rule;
    private CoreCaseData coreCaseData;

    @Before
    public void setup() {
        rule = new D8RespondentLastName();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8RespondentLastNameIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8RespondentLastNameIsNotNull() {
        coreCaseData.setD8RespondentLastName("Yes");

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
		rule.then();

        assertEquals("D8RespondentLastName can not be null or empty. Actual data is: null", rule.getResult().get(0));
    }
}