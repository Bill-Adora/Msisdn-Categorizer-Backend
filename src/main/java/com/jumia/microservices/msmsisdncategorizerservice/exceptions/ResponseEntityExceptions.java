package com.jumia.microservices.msmsisdncategorizerservice.exceptions;

import com.jumia.microservices.msmsisdncategorizerservice.models.ApiResponse;
import com.jumia.microservices.msmsisdncategorizerservice.utils.GlobalVariables;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.UUID;

/**
 * Handle Runtime Errors universally throughout the application.
 */
@ControllerAdvice
public class ResponseEntityExceptions extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();
        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        ex.getLocalizedMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.NOT_FOUND.value(), referenceId,
                        HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getLocalizedMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.NOT_FOUND.value(), referenceId,
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getLocalizedMessage(), null), HttpStatus.NOT_FOUND);
    }

    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        String referenceId = UUID.randomUUID().toString();

        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        JSONArray jsonArray = new JSONArray();
        for (FieldError fieldError : fieldErrors) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fieldName", fieldError.getField());
            jsonObject.put("error", fieldError.getDefaultMessage());
            jsonArray.put(jsonObject);
        }
        JSONObject node = new JSONObject();
        node.put("details", jsonArray);

        fieldErrors.toArray(new FieldError[0]);
        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        GlobalVariables.RESPONSE_INVALID_BODY_REQUEST_FORMAT, GlobalVariables.RESPONSE_MISSING_FIELDS,
                        node.toMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.INTERNAL_SERVER_ERROR.value(), referenceId,
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getLocalizedMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage(),
                        null), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage(),
                        null), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage(),
                        null), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String referenceId = UUID.randomUUID().toString();

        return new ResponseEntity<>(ApiResponse
                .responseFormatter(HttpStatus.BAD_REQUEST.value(), referenceId,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage(),
                        null), HttpStatus.BAD_REQUEST);
    }
}
