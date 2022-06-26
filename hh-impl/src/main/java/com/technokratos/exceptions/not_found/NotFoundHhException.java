package com.technokratos.exceptions.not_found;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class NotFoundHhException extends HhServiceException {
    public NotFoundHhException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
