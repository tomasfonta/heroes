package com.tomasfonta.heroes.config.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRoles {
    ADMIN(Sets.newHashSet(
            ApplicationPermission.HERO_CREATE,
            ApplicationPermission.HERO_DELETE,
            ApplicationPermission.HERO_READ,
            ApplicationPermission.HERO_UPDATE)),
    USER(Sets.newHashSet(
            ApplicationPermission.HERO_READ));

    private final Set<ApplicationPermission> permissions;

    ApplicationRoles(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }
}
