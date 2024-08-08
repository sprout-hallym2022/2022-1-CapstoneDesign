package com.example.geneweb.entity.research;

import lombok.Getter;

@Getter
public enum ResearchStatus {
    PROGRESS("진행 중"),
    COMPLETE("완료"),
    STOP("중단");

    private final String status;

    ResearchStatus(String status) {
        this.status = status;
    }
}
