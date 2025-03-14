package com.core.accountservice.customer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class CustomerExistResponseDTO {

    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("status")
    private final String status;

    public CustomerExistResponseDTO(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


}