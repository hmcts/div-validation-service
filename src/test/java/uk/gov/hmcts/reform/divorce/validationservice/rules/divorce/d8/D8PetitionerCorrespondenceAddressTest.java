package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.hmcts.reform.divorce.validationservice.domain.request.Address;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class D8PetitionerCorrespondenceAddressTest {

    private D8PetitionerCorrespondenceAddress rule;
    private CoreCaseData coreCaseData;

    @Before
    public void setup() {
        rule = new D8PetitionerCorrespondenceAddress();
        coreCaseData = new CoreCaseData();
    }

    @Test
    public void whenShouldReturnTrueWhenD8PetitionerCorrespondenceAddressIsNull() {
        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();
        
        assertEquals(true, result);
    }

    @Test
    public void whenShouldReturnFalseWhenD8PetitionerCorrespondenceAddressIsNotNull() {
        Address address = new Address();
        coreCaseData.setD8PetitionerCorrespondenceAddress(address);

        rule.setCoreCaseData(coreCaseData);
        boolean result = rule.when();

        assertEquals(false, result);
    }

    @Test
    public void thenShouldReturnErrorMessageWithNull() {
        rule.setCoreCaseData(coreCaseData);

        rule.then();

        assertEquals("D8PetitionerCorrespondenceAddress can not be null or empty. Actual data is: null", rule.getResult());
    }
}