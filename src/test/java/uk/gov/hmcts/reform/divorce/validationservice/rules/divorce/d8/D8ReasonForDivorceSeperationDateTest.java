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
public class D8ReasonForDivorceSeperationDateTest {

    private D8ReasonForDivorceSeperationDate rule;
    private CoreCaseData coreCaseData;

    @Before
    public void setup() {
        rule = new D8ReasonForDivorceSeperationDate();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenFactIsSeperation2YearsAndD8ReasonForDivorceSeperationDateIsNull() {
        coreCaseData.setD8ReasonForDivorce("separation-2-years");
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenFactIsNotSeperationAndD8ReasonForDivorceSeperationDateIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(false, result);
    }

    @Test
    public void whenShouldReturnTrueWhenFactIsSeperation5YearsAndD8ReasonForDivorceSeperationDateIsNull() {
        coreCaseData.setD8ReasonForDivorce("separation-5-years");
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8ReasonForDivorceSeperationDateIsNotNull() {
        coreCaseData.setD8ReasonForDivorceSeperationDate("dateString");

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8ReasonForDivorceSeperationDate can not be null or empty. Actual data is: null",
            rule.getResult().get(0));
    }
}