package com.epam.esm.controller.advice;


import com.epam.esm.repository.entity.ErrorResponse;
import com.epam.esm.service.exception.ResourceNotFoundException;
import com.epam.esm.service.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorCode = String.format("%s%s", BAD_REQUEST.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceValidationException.class})
    public ResponseEntity<ErrorResponse> handleResourceValidationException(ResourceValidationException e) {
        String errorCode = String.format("%s%s", BAD_REQUEST.value(), e.getResourceId());
        ErrorResponse errorResponse =
                new ErrorResponse(e.getMessage(), errorCode);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }


}
