package uk.gov.hmcts.reform.divorce.validationservice.rules.rulebooks;

import com.deliveredtechnologies.rulebook.NameValueReferableTypeConvertibleMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.CoreCaseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class D8RuleBook extends CoRRuleBook<List<String>> {

    private List<String> errors = new ArrayList<>();

    private final String coreCaseData = "coreCaseData";

    @Override
    public void defineRules() {

        setDefaultResult(null);

        addRule(RuleBuilder.create().withFactType(CoreCaseData.class).withResultType(List.class)
                .when(facts -> isNotValidD8Process(getCoreCaseData(facts).getD8legalProcess()))
                .then(this::d8LegalProcessErrored)
                .build());

        addRule(RuleBuilder.create().withFactType(CoreCaseData.class).withResultType(List.class)
                .when(facts -> isNotValidMarriageDate(getCoreCaseData(facts).getD8MarriageDate()))
                .then(this::d8MarriageDateErrored)
                .build());
    }

    private void d8LegalProcessErrored(NameValueReferableTypeConvertibleMap<CoreCaseData> nameValueReferable,
                                       Result<List> result) {
        errors.add("D8 Legal Process Error " + nameValueReferable.getValue(coreCaseData).getD8legalProcess());
       result.setValue(errors);
    }

    private void d8MarriageDateErrored(NameValueReferableTypeConvertibleMap<CoreCaseData> nameValueReferable,
                                       Result<List> result) {
        errors.add("D8MarriageDate not valid " + nameValueReferable.getValue(coreCaseData).getD8MarriageDate());
        result.setValue(errors);
    }

    private CoreCaseData getCoreCaseData(NameValueReferableTypeConvertibleMap<CoreCaseData> facts) {
        return facts.getValue(coreCaseData);
    }

    private boolean isNotValidMarriageDate(String coreCaseData) {
        return Optional.ofNullable(coreCaseData)
                .map(this::parseToInstant)
                .map(instant -> instant.isAfter(Instant.now())
                       || instant.isBefore(ZonedDateTime.now().minusYears(100).toInstant()))
                .orElse(true);
    }

    private Instant parseToInstant(String date) {
        Instant instant = null;
        try {
            instant = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(date).toInstant();
        } catch (ParseException e) {
            log.error("failed to parse date to instant ", e);
        }
        return instant;
    }

    private boolean isNotValidD8Process(String value) {
        return Stream.of("Divorce", "Judicial Separation", "Nullity").noneMatch(i -> i.equals(value));
    }
}
