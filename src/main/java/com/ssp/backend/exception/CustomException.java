package com.ssp.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Slf4j
public class CustomException extends RuntimeException{

    private final HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        log.error(message+" -> "+status);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
