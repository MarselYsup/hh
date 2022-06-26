package com.technokratos.exceptions.token;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class TokenHhException extends HhServiceException {
    public TokenHhException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
