package com.tomasfonta.heroes.error;

import org.springframework.http.HttpStatus;

public class HeroNotFoundException extends ValidationException {
    public HeroNotFoundException(String message) {
        super(ValidationType.NOTFOUND, HttpStatus.NOT_FOUND, message);
    }
}


