package com.jumia.microservices.msmsisdncategorizerservice.controllers;

import com.jumia.microservices.msmsisdncategorizerservice.config.ConfigProperties;
import com.jumia.microservices.msmsisdncategorizerservice.utils.GlobalVariables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
    private MockMvc mockMvc;

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testThatControllerReturnsExpectedResponseWithoutParams()  throws Exception{
        mockMvc.perform(get("/api/v1/getPhoneNumbers")
                    .header(HttpHeaders.AUTHORIZATION, "Basic " + getCredentials())
                    .header(GlobalVariables.X_CORRELATION_ID, UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.responseCode", is(200)))
                .andExpect(jsonPath("$.body.data", hasSize(41)))
                .andReturn();
    }

    @Test
    public void givenValidParamsTestThatControllerRespondsSuccessfully()  throws Exception{
        mockMvc.perform(get("/api/v1/getPhoneNumbers")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + getCredentials())
                .param("country", "Ethiopia")
                .param("state", "Valid")
                .param("page", "1")
                .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.responseCode", is(200)))
                .andExpect(jsonPath("$.body.data[0].country", is("Ethiopia")))
                .andExpect(jsonPath("$.body.data[0].state", is("VALID")))
                .andReturn();
    }

    private byte[] getCredentials() {
        return Base64.getEncoder().encode((configProperties.getUsername() + ":" + configProperties.getPassword()).getBytes());
    }
}
