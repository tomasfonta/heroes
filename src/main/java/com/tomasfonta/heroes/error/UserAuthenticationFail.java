package com.tomasfonta.heroes.error;

public class UserAuthenticationFail extends RuntimeException {

    public UserAuthenticationFail(String message) {
        super(message);
    }

}
