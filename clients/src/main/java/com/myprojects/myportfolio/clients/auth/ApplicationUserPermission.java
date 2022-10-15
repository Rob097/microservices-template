package com.myprojects.myportfolio.clients.auth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApplicationUserPermission {
    USERS_READ(1, "users_read"),
    USERS_WRITE(2, "users_write");

    private final Integer id;
    private final String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
