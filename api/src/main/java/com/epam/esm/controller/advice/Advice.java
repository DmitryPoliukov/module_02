package com.epam.esm.controller.advice;


import com.epam.esm.repository.entity.ErrorResponse;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorCode = String.format("%s%s", NOT_FOUND.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceValidationException.class})
    public ResponseEntity<ErrorResponse> handleResourceValidationException(ResourceValidationException e) {
        String errorCode = String.format("%s%s", INTERNAL_SERVER_ERROR.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
        String errorCode = String.format("%s%s", INTERNAL_SERVER_ERROR.value(), 00);
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }


}
