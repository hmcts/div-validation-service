package uk.gov.hmcts.reform.divorce.validationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@ApiModel(description = "Request body model for validation service Request")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationRequest {

    @ApiModelProperty(value = "Id of the form to be validated", required = true)
    @JsonProperty(value = "formId", required = true)
    @NotBlank
    private final String formId;

    @ApiModelProperty(value = "Section of the form to be validated")
    @JsonProperty(value = "sectionId")
    private final String section;

    @JsonProperty(value = "data", required = true)
    @ApiModelProperty(value = "data to be validated", required = true)
    private final CoreCaseData data;

}