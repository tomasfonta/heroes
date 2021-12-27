package com.tomasfonta.heroes.config.security;

public enum ApplicationPermission {

    HERO_READ("HERO_READ"),
    HERO_UPDATE("HERO_UPDATE"),
    HERO_DELETE("HERO_DELETE"),
    HERO_CREATE("HERO_CREATE");

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
