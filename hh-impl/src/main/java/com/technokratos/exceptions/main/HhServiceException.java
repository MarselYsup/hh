package com.technokratos.exceptions.main;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HhServiceException extends RuntimeException{
    private final HttpStatus httpStatus;

    public HhServiceException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
