package uk.gov.hmcts.reform.divorce.validationservice.rules.divorce.session;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import lombok.Data;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.DivorceSession;

import java.util.List;
import java.util.Optional;

@Rule(order = 19)
@Data
public class ReasonForDivorceSeperationDate {

    private static final String BLANK_SPACE = " ";
    private static final String REASON_SEPARATION_2_YEARS = "separation-2-years";
    private static final String REASON_SEPARATION_5_YEARS = "separation-5-years";
    private static final String ACTUAL_DATA = "Actual data is: %s";
    private static final String ERROR_MESSAGE = "reasonForDivorceSeperationDate can not be null or empty.";

    @Result
    public List<String> result;

    @Given("divorceSession")
    public DivorceSession divorceSession = new DivorceSession();

    @When
    public boolean when() {
        return (Optional.ofNullable(divorceSession.getReasonForDivorce()).orElse("")
                .equalsIgnoreCase(REASON_SEPARATION_2_YEARS)
            || Optional.ofNullable(divorceSession.getReasonForDivorce()).orElse("")
                .equalsIgnoreCase(REASON_SEPARATION_5_YEARS))
            && !Optional.ofNullable(divorceSession.getReasonForDivorceSeperationDate()).isPresent();
    }

    @Then
    public void then() {
        result.add(String.join(
            BLANK_SPACE, // delimiter
            ERROR_MESSAGE,
            String.format(ACTUAL_DATA, divorceSession.getReasonForDivorceSeperationDate())
        ));
    }
}