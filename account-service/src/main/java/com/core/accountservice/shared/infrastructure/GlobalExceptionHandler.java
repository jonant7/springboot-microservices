package com.core.accountservice.shared.infrastructure;

import com.core.accountservice.account.domain.exceptions.AccountAlreadyExistsException;
import com.core.accountservice.movement.domain.exceptions.InsufficientFundsException;
import com.core.accountservice.movement.domain.exceptions.MovementAlreadyExistsException;
import com.core.accountservice.shared.domain.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseWrapper<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"));
    }

    @ExceptionHandler({AccountAlreadyExistsException.class, MovementAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseWrapper<String>> handleEntityAlreadyExists(AccountAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseWrapper<String>> handleInsufficientFundsException(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
