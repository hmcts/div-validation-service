package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;
import uk.gov.hmcts.reform.divorce.validationservice.utils.DateUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class D8MarriageDateTest {

    private D8MarriageDate rule;
    private CoreCaseData coreCaseData;

    @Before
    public void setup() {
        rule = new D8MarriageDate();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8MarriageDateIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnTrueWhenD8MarriageDateLessThanOneYearAgo() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().minus(100, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnTrueWhenD8MarriageDateMoreThanOneHundredYearsAgo() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().minus(365 * 105, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnTrueWhenD8MarriageDateIsInTheFuture() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().plus(100, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8MarriageDateIsValid() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().minus(365 * 2, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8MarriageDate can not be null or empty. Actual data is: null", rule.getResult().get(0));
    }

    @Test
    public void thenShouldReturnErrorMessageWhenD8MarriageDateLessThanOneYearAgo() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().minus(100, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);
        
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8MarriageDate can not be less than one year ago. Actual data is: ".concat(d8MarriageDate),
            rule.getResult().get(0));
    }

    @Test
    public void thenShouldReturnErrorMessageWhenD8MarriageDateMoreThanOneHundredYearsAgo() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().minus(365 * 105, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);
        
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8MarriageDate can not be more than 100 years ago. Actual data is: ".concat(d8MarriageDate),
            rule.getResult().get(0));
    }

    @Test
    public void thenShouldReturnErrorMessageWhenD8MarriageDateIsInTheFuture() {
        String d8MarriageDate = DateUtils.getFormattedDate(Instant.now().plus(100, ChronoUnit.DAYS));
        coreCaseData.setD8MarriageDate(d8MarriageDate);
        
        rule.setCoreCaseData(coreCaseData);

        rule.setResult(new ArrayList<>());
        rule.then();

        assertEquals("D8MarriageDate can not be in the future. Actual data is: ".concat(d8MarriageDate),
            rule.getResult().get(0));
    }
}