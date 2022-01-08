package com.tomasfonta.heroes.error;

import org.springframework.http.HttpStatus;

public class PowerStatNotFoundExpetion extends ValidationException {
    public PowerStatNotFoundExpetion(String message) {
        super(ValidationType.NOTFOUND, HttpStatus.NOT_FOUND, message);
    }
}
