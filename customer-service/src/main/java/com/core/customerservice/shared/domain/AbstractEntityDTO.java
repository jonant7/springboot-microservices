package com.core.customerservice.shared.domain;

import lombok.Getter;

@Getter
public class AbstractEntityDTO {

    private final Long id;

    protected AbstractEntityDTO(Long id) {
        this.id = id;
    }
}
