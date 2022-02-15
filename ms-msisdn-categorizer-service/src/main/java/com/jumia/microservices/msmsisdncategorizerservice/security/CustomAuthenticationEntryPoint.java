package com.jumia.microservices.msmsisdncategorizerservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.microservices.msmsisdncategorizerservice.models.ApiResponse;
import com.jumia.microservices.msmsisdncategorizerservice.utils.GlobalVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException ex) throws IOException {

        String requestRefId = UUID.randomUUID().toString();

        logger.info("TransactionID={} | TransactionType={} | ErrorDescription={}",
                requestRefId, GlobalVariables.RESPONSE_USER_NOT_AUTHORIZED, ex.getMessage());

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.print(objectMapper.writeValueAsString(ApiResponse
                .responseFormatter(GlobalVariables.RESPONSE_CODE_401, requestRefId, "Sorry, You're not authorized to access this resource.",
                        "Sorry, You're not authorized to access this resource.", null)));
        writer.flush();
    }
}