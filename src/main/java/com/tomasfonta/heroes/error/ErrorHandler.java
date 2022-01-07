package com.tomasfonta.heroes.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> validationException(ValidationException e,
                                                                 WebRequest request) {
        log.error(String.format(" :::::::::: Error: %s, from Request: %s ::::::::::",
                e.getErrorMessage(), request.getContextPath()));
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setType(e.getValidationType());
        exResponse.setDescription(e.getErrorMessage());
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAuthenticationFail.class , AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> AuthenticationException(RuntimeException e,
                                                                     WebRequest request) {
        log.error(String.format(" ------- Error: %s, from Request: %s",
                e.getMessage(), request.getContextPath()));
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setType(ValidationType.AUTHENTICATION);
        exResponse.setDescription(e.getMessage());
        return new ResponseEntity<>(exResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception e, WebRequest request) {
        log.error(String.format(" ------- Error: %s, Exception: %s, from Request: %s -------",
                e.getMessage(), e.getClass().getSimpleName(), request.getContextPath()));
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setType(ValidationType.ERROR);
        exResponse.setDescription(e.getMessage());
        return new ResponseEntity<>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}