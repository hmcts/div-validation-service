package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.d8;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import lombok.Data;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import java.util.Optional;

@Rule
@Data
public class D8LegalProcess {

    @Result
    public String result;
    @Given("coreCaseData")
    private CoreCaseData coreCaseData = new CoreCaseData();

    @When
    public boolean when() {
        return Optional.ofNullable(coreCaseData.getD8LegalProceedings())
                .map(value -> !"divorce".equals(value)).orElse(false);
    }

    @Then
    public void then() {
        result = "D8 Legal Process";
    }

}
