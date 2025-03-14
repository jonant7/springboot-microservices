package com.core.accountservice.movement.domain.exceptions;

public class MovementNotFoundException extends RuntimeException {
    public MovementNotFoundException(Long id) {
        super(String.format("Movement with id %s not found", id));
    }
}
