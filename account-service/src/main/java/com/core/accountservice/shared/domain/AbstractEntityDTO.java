package com.core.accountservice.shared.domain;

import lombok.Getter;

@Getter
public class AbstractEntityDTO {

    private final Long id;

    protected AbstractEntityDTO(Long id) {
        this.id = id;
    }
}
