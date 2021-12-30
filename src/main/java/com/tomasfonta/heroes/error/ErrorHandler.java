package com.tomasfonta.heroes.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> validationException(ValidationException e,
                                                                 WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setType(e.getValidationType());
        exResponse.setDescription(e.getErrorMessage());
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception e, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setType(ValidationType.ERROR);
        exResponse.setDescription(e.getMessage());
        return new ResponseEntity<>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}