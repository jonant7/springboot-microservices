package com.core.customerservice.customer.domain.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Person with id %s not found", id));
    }
}
