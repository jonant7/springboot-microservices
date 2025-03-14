package com.core.customerservice.customer.domain.dtos;

import com.core.customerservice.customer.domain.Customer;
import lombok.Getter;

@Getter
public class CustomerExistResponseDTO {

    private final Long id;
    private final String name;
    private final String status;

    public CustomerExistResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getPerson().getName();
        this.status = customer.getStatus().name();
    }
}