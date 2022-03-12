package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Set.*;


public enum Role {
    USER(of(Permission.DEVELOPERS_READ)),
    ADMIN(of(Permission.DEVELOPERS_WRITE , Permission.DEVELOPERS_READ));

    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorites(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
