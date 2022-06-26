package com.technokratos.exceptions.unauthorized;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class UnauthorizedHhException extends HhServiceException {
    public UnauthorizedHhException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
