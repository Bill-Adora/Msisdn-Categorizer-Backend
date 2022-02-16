package com.jumia.microservices.msmsisdncategorizerservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to standardize the structure of the responses returned by this service.
 */
public class ModelApiResponse {
    @JsonProperty("header")
    private ApiHeaderResponse apiHeaderResponse;

    @JsonProperty("body")
    private Object responseBodyObject;

    public ModelApiResponse() {
        this.apiHeaderResponse = new ApiHeaderResponse();
    }

    public Object getResponseBodyObject() {
        return responseBodyObject;
    }

    public void setResponseBodyObject(Object responseBodyObject) {
        this.responseBodyObject = responseBodyObject;
    }

    public ApiHeaderResponse getApiHeaderResponse() {
        return apiHeaderResponse;
    }

    public void setApiHeaderResponse(ApiHeaderResponse apiHeaderResponse) {
        this.apiHeaderResponse = apiHeaderResponse;
    }

    /**
     * Defines the header of the response
     */
    public class ApiHeaderResponse {

        @JsonProperty("requestRefId")
        private String requestRefId;

        @JsonProperty("responseCode")
        private int responseCode;

        @JsonProperty("responseMessage")
        private String responseMessage;

        @JsonProperty("customerMessage")
        private String customerMessage;

        @JsonProperty("timestamp")
        private String timestamp;

        ApiHeaderResponse() {
        }

        public Integer getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public String getCustomerMessage() {
            return customerMessage;
        }

        public void setCustomerMessage(String customerMessage) {
            this.customerMessage = customerMessage;
        }

        public String getRequestRefId() {
            return requestRefId;
        }

        public void setRequestRefId(String requestRefId) {
            this.requestRefId = requestRefId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}