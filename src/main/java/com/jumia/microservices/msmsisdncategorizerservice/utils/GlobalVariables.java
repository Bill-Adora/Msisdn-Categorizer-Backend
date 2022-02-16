package com.jumia.microservices.msmsisdncategorizerservice.utils;

/**
 * Holds global variables that are shared among various classes
 */
public class GlobalVariables {
    public static final String X_CORRELATION_ID = "X-Correlation-ConversationID";

    /* =====================================
     * COUNTRIES
     * ======================================*/
    public static final String CAMEROON = "Cameroon";
    public static final String ETHIOPIA = "Ethiopia";
    public static final String MOROCCO = "Morocco";
    public static final String MOZAMBIQUE = "Mozambique";
    public static final String UGANDA = "Uganda";

    /* =====================================
     * COUNTRY CODES
     * ======================================*/
    public static final String CAMEROONCOUNTRYCODE = "+237";
    public static final String ETHIOPIACOUNTRYCODE = "+251";
    public static final String MOROCCOCOUNTRYCODE = "+212";
    public static final String MOZAMBIQUECOUNTRYCODE = "+258";
    public static final String UGANDACOUNTRYCODE = "+256";

    /* =====================================
     * COUNTRY REGEX
     * ======================================*/
    public static final String CAMEROONREGEX = "\\(237\\)\\ ?[2368]\\d{7,8}$";
    public static final String ETHIOPIAREGEX = "\\(251\\)\\ ?[1-59]\\d{8}$";
    public static final String MOROCCOREGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";
    public static final String MOZAMBIQUEREGEX = "\\(258\\)\\ ?[28]\\d{7,8}$";
    public static final String UGANDAREGEX = "\\(256\\)\\ ?\\d{9}$";

    /* =====================================
     * RESPONSE MESSAGES
     * ======================================*/
    public static final String RESPONSE_USER_NOT_AUTHORIZED = "user-not-authorized";
    public static final String RESPONSE_FAIL = "FAILED";
    public static final String RESPONSE_SUCCESS = "SUCCESS";
    public static final String RESPONSE_INVALID_BODY_REQUEST_FORMAT = "Invalid request body format";
    public static final String RESPONSE_MISSING_FIELDS = "Missing Required Fields. Please insert the required fields";

    /* =====================================
     * RESPONSE CODES
     * =====================================*/
    public static final int RESPONSE_CODE_200 = 200;
    public static final int RESPONSE_CODE_401 = 401;

    /* =====================================
     * SERVICE ACTIONS
     * ======================================*/
    public static final String GET_PHONE_NUMBERS = "get-phone-numbers";

}
