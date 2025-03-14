package com.core.accountservice.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseWrapper<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseWrapper<T> of(HttpStatus status, String message, T data) {
        return new ResponseWrapper<>(status.value(), message, data);
    }

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(HttpStatus.OK.value(), "Success", data);
    }

    public static <T> ResponseWrapper<T> error(HttpStatus status, String message) {
        return new ResponseWrapper<>(status.value(), message, null);
    }
}
