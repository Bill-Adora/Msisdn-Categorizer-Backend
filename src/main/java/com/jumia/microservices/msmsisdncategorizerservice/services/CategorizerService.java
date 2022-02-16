package com.jumia.microservices.msmsisdncategorizerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.microservices.msmsisdncategorizerservice.datalayer.entity.CustomerModel;
import com.jumia.microservices.msmsisdncategorizerservice.datalayer.repository.CustomerRepository;
import com.jumia.microservices.msmsisdncategorizerservice.models.*;
import com.jumia.microservices.msmsisdncategorizerservice.utils.GlobalVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CategorizerService {
    private static final Logger logger = LoggerFactory.getLogger(CategorizerService.class);
    private CustomerRepository customerRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CategorizerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseEntity<ModelApiResponse> getPhoneNumbers(String requestRefId, Optional<String> optionalCountry,
            Optional<String> optionalState, Optional<Integer> optionalPage, Optional<Integer> optionalPageSize) throws JsonProcessingException {
        List<CustomerModel> customerModels = customerRepository.findAllByOrderByIdAsc();

        List<PhoneNumberResponseObject> phoneNumberList = customerModels.stream()
                .map(customer -> categorizePhoneNumber(customer.getPhone()))
                .filter(Objects::nonNull)
                .filter(phoneNumberResponseObject -> {
                    if(optionalCountry.isPresent() && !optionalCountry.get().isEmpty()) {
                        if(phoneNumberResponseObject.getCountry().equalsIgnoreCase(optionalCountry.get())) {
                            return  true;
                        }
                        return false;
                    }
                    return true; })
                .filter(phoneNumberResponseObject -> {
                    if(optionalState.isPresent() && !optionalState.get().isEmpty()) {
                        if(phoneNumberResponseObject.getState().toString().equalsIgnoreCase(optionalState.get())) {
                            return true;
                        }
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());

        PagedResponse pagedResponse = paginateResponse(phoneNumberList, optionalPage, optionalPageSize);

        logger.info("TransactionID={} | TransactionType={} | Response={} | ResponsePayload={}",
                requestRefId, GlobalVariables.GET_PHONE_NUMBERS, GlobalVariables.RESPONSE_SUCCESS, objectMapper.writeValueAsString(pagedResponse));

        return new ResponseEntity<>(ApiResponse.responseFormatter(GlobalVariables.RESPONSE_CODE_200, requestRefId,
                "Phone numbers retrieved successfully", "Phone numbers retrieved successfully",
                pagedResponse),HttpStatus.OK);
    }

    /**
     * Validates and categorizes phone numbers according to their countries
     * @param phoneNumber
     * @return PhoneNumberResponseObject
     */
    private PhoneNumberResponseObject categorizePhoneNumber(String phoneNumber) {
        String numberPrefix = getPhoneNumberPrefix(phoneNumber);
        Pattern pattern;

        //If the phone number belongs to Cameroon
        if(numberPrefix.equals(GlobalVariables.CAMEROONCOUNTRYCODE)) {
            pattern = Pattern.compile(GlobalVariables.CAMEROONREGEX);

            return new PhoneNumberResponseObject(GlobalVariables.CAMEROON,
                    pattern.matcher(phoneNumber).matches() ? State.VALID : State.INVALID,
                    GlobalVariables.CAMEROONCOUNTRYCODE,
                    phoneNumber);
        }

        //If the phone number belongs to Ethiopia
        if(numberPrefix.equals(GlobalVariables.ETHIOPIACOUNTRYCODE)) {
            pattern = Pattern.compile(GlobalVariables.ETHIOPIAREGEX);

            return new PhoneNumberResponseObject(GlobalVariables.ETHIOPIA,
                    pattern.matcher(phoneNumber).matches() ? State.VALID : State.INVALID,
                    GlobalVariables.ETHIOPIACOUNTRYCODE,
                    phoneNumber);
        }

        //If the phone number belongs to Morocco
        if(numberPrefix.equals(GlobalVariables.MOROCCOCOUNTRYCODE)) {
            pattern = Pattern.compile(GlobalVariables.MOROCCOREGEX);

            return new PhoneNumberResponseObject(GlobalVariables.MOROCCO,
                    pattern.matcher(phoneNumber).matches() ? State.VALID : State.INVALID,
                    GlobalVariables.MOROCCOCOUNTRYCODE,
                    phoneNumber);
        }

        //If the phone number belongs to Mozambique
        if(numberPrefix.equals(GlobalVariables.MOZAMBIQUECOUNTRYCODE)) {
            pattern = Pattern.compile(GlobalVariables.MOZAMBIQUEREGEX);

            return new PhoneNumberResponseObject(GlobalVariables.MOZAMBIQUE,
                    pattern.matcher(phoneNumber).matches() ? State.VALID : State.INVALID,
                    GlobalVariables.MOZAMBIQUECOUNTRYCODE,
                    phoneNumber);
        }

        //If the phone number belongs to Uganda
        if(numberPrefix.equals(GlobalVariables.UGANDACOUNTRYCODE)) {
            pattern = Pattern.compile(GlobalVariables.UGANDAREGEX);

            return new PhoneNumberResponseObject(GlobalVariables.UGANDA,
                    pattern.matcher(phoneNumber).matches() ? State.VALID : State.INVALID,
                    GlobalVariables.UGANDACOUNTRYCODE,
                    phoneNumber);
        }

        //If the phone number doesn't belong to any of our listed countries
        return null;
    }

    /**
     * Get the number prefix to compare with country codes
     * @param phoneNumber
     * @return String
     */
    private String getPhoneNumberPrefix(String phoneNumber) {
        return "+" + phoneNumber.substring(1, 4);
    }

    /**
     * Return list of paginated phone numbers
     * @param phoneNumberList
     * @param optionalPage
     * @param optionalPageSize
     * @return PagedResponse
     */
    private PagedResponse paginateResponse(List<PhoneNumberResponseObject> phoneNumberList, Optional<Integer> optionalPage, Optional<Integer> optionalPageSize) {
        if(phoneNumberList.isEmpty())
            return new PagedResponse(0, 0,false, false, 0, Collections.emptyList());

        //We subtract one from the page provided because  the list index starts from zero
        int page = (optionalPage.isPresent() && optionalPage.get() - 1 >= 0) ? optionalPage.get() - 1 : 0;
        int pageSize = (optionalPageSize.isPresent() && optionalPageSize.get() > 0) ? optionalPageSize.get() : phoneNumberList.size();
        int numOfPages = (int) Math.ceil((float) phoneNumberList.size() / (float) pageSize);
        page = (page >= numOfPages) ? numOfPages - 1 : page;

        List<PhoneNumberResponseObject> pagedList = phoneNumberList.subList(page * pageSize, Math.min(++page * pageSize, phoneNumberList.size()));

        return new PagedResponse(numOfPages, page, ((numOfPages != 1) && (numOfPages > page)) ? true : false, (page == 1) ? false : true, phoneNumberList.size(), pagedList);
    }
}
