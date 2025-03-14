package com.core.accountservice.shared.domain;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE(Boolean.TRUE),
    INACTIVE(Boolean.FALSE);

    private final Boolean    value;

    Status(Boolean value) {
        this.value = value;
    }

    public static Status fromBoolean(Boolean value) {
        return value ? ACTIVE : INACTIVE;
    }
}
