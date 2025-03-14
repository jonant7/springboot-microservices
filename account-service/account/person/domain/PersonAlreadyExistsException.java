package com.core.customerservice.person.domain;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(String identification) {
        super(String.format("Person with id %s already exists", identification));
    }
}
