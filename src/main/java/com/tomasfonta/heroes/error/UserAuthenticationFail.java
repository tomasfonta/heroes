package com.tomasfonta.heroes.error;

import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationFail extends AuthenticationException {

    public UserAuthenticationFail(String message) {
        super(message);
    }

}
