package com.tomasfonta.heroes.config.jwt;

import lombok.Data;

@Data
public class UserAuthenticationRequest {

    private String name;
    private String password;

}
