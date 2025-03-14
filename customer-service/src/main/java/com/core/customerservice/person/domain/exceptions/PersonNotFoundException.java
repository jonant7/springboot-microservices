package com.core.customerservice.person.domain.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super(String.format("Person with id %s not found", id));
    }
}
