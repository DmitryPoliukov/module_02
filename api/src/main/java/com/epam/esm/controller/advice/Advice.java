package com.epam.esm.controller.advice;


import com.epam.esm.repository.entity.ErrorResponse;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorCode = String.format("%s%s", NOT_FOUND.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceValidationException.class})
    public ResponseEntity<ErrorResponse> handleResourceValidationException(ResourceValidationException e) {
        String errorCode = String.format("%s%s", BAD_REQUEST.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
        String errorCode = String.format("%s%s", INTERNAL_SERVER_ERROR.value(), 00);
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }
}
