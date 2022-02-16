package com.jumia.microservices.msmsisdncategorizerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jumia.microservices.msmsisdncategorizerservice.models.ModelApiResponse;
import com.jumia.microservices.msmsisdncategorizerservice.services.CategorizerService;
import com.jumia.microservices.msmsisdncategorizerservice.utils.GlobalVariables;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@Api(value = "categorizer-controller", tags = {"categorizer-controller"})
public class CategorizerController {
    private CategorizerService categorizerService;

    @Autowired
    public CategorizerController(CategorizerService categorizerService) {
        this.categorizerService = categorizerService;
    }

    @ApiOperation(value = "Get categorized phone numbers.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Phone numbers retrieved successfully.", response = ModelApiResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized access.", response = ModelApiResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = ModelApiResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ModelApiResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = GlobalVariables.X_CORRELATION_ID, value = "820d3e36-383d-404c-9058-3777c972c2ce", paramType = "header"),
    })
    @RequestMapping(value = "/getPhoneNumbers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ModelApiResponse> getPhoneNumbers(
            @RequestHeader HttpHeaders headers,
            @ApiParam(value = "Country whose numbers should be retrieved.")
            @RequestParam(name = "country") Optional<String> optionalCountry,
            @ApiParam(value = "Validity state of the numbers to retrieve.")
            @RequestParam(name = "state") Optional<String> optionalState,
            @ApiParam(value = "Page number to be used for pagination.")
            @RequestParam(name = "page") Optional<Integer> optionalPage,
            @ApiParam(value = "Size of page to be used for pagination.")
            @RequestParam(name = "pageSize") Optional<Integer> optionalPageSize) throws JsonProcessingException {
        String requestRefId;

        if (headers.containsKey(GlobalVariables.X_CORRELATION_ID)) {
            requestRefId = headers.getFirst(GlobalVariables.X_CORRELATION_ID);
        } else {
            requestRefId = UUID.randomUUID().toString();
        }

        return categorizerService.getPhoneNumbers(requestRefId, optionalCountry, optionalState, optionalPage, optionalPageSize);
    }
}
