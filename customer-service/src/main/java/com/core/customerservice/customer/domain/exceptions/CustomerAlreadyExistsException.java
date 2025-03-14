package com.core.customerservice.customer.domain.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String identification) {
        super(String.format("Person with id %s already exists", identification));
    }
}
