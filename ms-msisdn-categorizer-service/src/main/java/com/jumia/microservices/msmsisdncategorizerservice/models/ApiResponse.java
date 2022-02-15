package com.jumia.microservices.msmsisdncategorizerservice.models;

import java.sql.Timestamp;

public class ApiResponse extends ModelApiResponse {
    /**
     * Format the response Object according to the {@code} ModelApiResponse standard.
     *
     * @param responseCode
     * @param referenceId
     * @param message
     * @param description
     * @param responseObject
     * @return ApiResponse
     */
    public static ApiResponse responseFormatter(
            int responseCode, String referenceId, String message, String description, Object responseObject){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.getApiHeaderResponse().setResponseCode(responseCode);
        apiResponse.getApiHeaderResponse().setRequestRefId(referenceId);
        apiResponse.getApiHeaderResponse().setResponseMessage(message);
        apiResponse.getApiHeaderResponse().setCustomerMessage(description);
        apiResponse.getApiHeaderResponse().setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        if(responseObject != null){
            apiResponse.setResponseBodyObject(responseObject);
        }
        return apiResponse;
    }

}
