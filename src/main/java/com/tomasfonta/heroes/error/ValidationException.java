package com.tomasfonta.heroes.error;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {
    private final HttpStatus status;
    private final String errorMessage;
    private final ValidationType validationType;


    public ValidationException(ValidationType validationType,
                               HttpStatus status,
                               String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.validationType = validationType;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

}


