package ru.tbcarus.funnyqueue.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    SUPERUSER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
