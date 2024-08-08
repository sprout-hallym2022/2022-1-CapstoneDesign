package com.example.geneweb.entity.account;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_INACTIVE_USER("INACTIVE USER"),
    ROLE_USER("RESEARCHER"),
    ROLE_ADMIN("ADMIN");

    private String authority;

    Authority(String string) {
        this.authority = authority;
    }
}
