package com.example.geneweb.entity.account;

import lombok.Getter;

// 연구에 대한 접근 권한
@Getter
public enum AccountStatus {
    SUBMITTED("승인 요청"),
    ACCEPT("승인"),
    REJECT("거절");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }
}
