package com.technokratos.exceptions.bad_request;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class BadRequestHhException extends HhServiceException {
    public BadRequestHhException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
