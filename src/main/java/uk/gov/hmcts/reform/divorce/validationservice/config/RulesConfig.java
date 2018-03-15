package uk.gov.hmcts.reform.divorce.validationservice.config;

import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.divorce.validationservice.rules.rulebooks.D8RuleBook;

import java.util.List;

/**
 * Created by mrganeshraja on 28/02/2018.
 */
@Configuration
public class RulesConfig {

    public static final String D8_RULEBOOK = "uk.gov.hmcts.reform.divorce.validationservice.rules.d8";

    @Bean("springD8RuleBook")
    public RuleBook d8RuleBook() {
        return new RuleBookRunner(D8_RULEBOOK);
    }

    @Bean("javaD8RuleBook")
    public RuleBook d8JavaRuleBook() {
        return RuleBookBuilder.create(D8RuleBook.class).withResultType(List.class)
                .withDefaultResult(null)
                .build();
    }
}
