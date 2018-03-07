package uk.gov.hmcts.reform.divorce.validationservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.divorce.validationservice.domain.request.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.response.ValidationResponse;
import uk.gov.hmcts.reform.divorce.validationservice.service.ValidationService;

import javax.validation.Valid;

@RestController
@Api(value = "Divorce Validation Service", tags = {"Divorce Validation Service"})
@Slf4j
public class ValidationServiceController {

    @Autowired
    private ValidationService validationService;

    @ApiOperation(value = "Validates a case details against business rules.", tags = {"Divorce Validation Service"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Validation Success", response = ValidationResponse.class)
        })
    @PostMapping("/version/1/validate")
    public ValidationResponse validate(@RequestBody @Valid @ApiParam(value = "Validation Request", required = true)
                                               ValidationRequest validationRequest) {
        return validationService.validate(validationRequest);
    }
}