package com.jumia.microservices.msmsisdncategorizerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.microservices.msmsisdncategorizerservice.models.ModelApiResponse;
import com.jumia.microservices.msmsisdncategorizerservice.models.PagedResponse;
import com.jumia.microservices.msmsisdncategorizerservice.models.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorizerServiceTest {
    @Autowired
    CategorizerService categorizerService;
    private ObjectMapper objectMapper = new ObjectMapper();

    private String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void testPhoneNumbersRetrievedSuccessfullyWithNullOptionals() throws Exception {
        String requestRefId = UUID.randomUUID().toString();
        ResponseEntity<ModelApiResponse> result = categorizerService.getPhoneNumbers(requestRefId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = mapToJson(result.getBody().getResponseBodyObject());
        PagedResponse pagedResponse = mapFromJson(jsonResponse, PagedResponse.class);

        assertTrue(pagedResponse.getData().size() > 0);
    }

    @Test
    public void givenValidCountryTestThatFilterByCountryIsSuccessful() throws Exception {
        String requestRefId = UUID.randomUUID().toString();
        ResponseEntity<ModelApiResponse> result = categorizerService.getPhoneNumbers(requestRefId, Optional.of("Cameroon"), Optional.empty(), Optional.empty(), Optional.empty());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = mapToJson(result.getBody().getResponseBodyObject());
        PagedResponse pagedResponse = mapFromJson(jsonResponse, PagedResponse.class);

        assertThat(pagedResponse.getData().get(0).getCountry()).isEqualTo("Cameroon");
    }

    @Test
    public void givenStateTestThatFilterByStateIsSuccessful() throws Exception {
        String requestRefId = UUID.randomUUID().toString();
        ResponseEntity<ModelApiResponse> result = categorizerService.getPhoneNumbers(requestRefId, Optional.empty(), Optional.of("Valid"), Optional.empty(), Optional.empty());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = mapToJson(result.getBody().getResponseBodyObject());
        PagedResponse pagedResponse = mapFromJson(jsonResponse, PagedResponse.class);

        assertThat(pagedResponse.getData().get(0).getState()).isEqualTo(State.VALID);
    }

    @Test
    public void givenPageAndPageSizeTestThatPaginationIsSuccessful() throws Exception {
        String requestRefId = UUID.randomUUID().toString();
        ResponseEntity<ModelApiResponse> result = categorizerService.getPhoneNumbers(requestRefId, Optional.empty(), Optional.empty(), Optional.of(1), Optional.of(10));

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = mapToJson(result.getBody().getResponseBodyObject());
        PagedResponse pagedResponse = mapFromJson(jsonResponse, PagedResponse.class);

        assertTrue(pagedResponse.getData().size() == 10);
    }
}
