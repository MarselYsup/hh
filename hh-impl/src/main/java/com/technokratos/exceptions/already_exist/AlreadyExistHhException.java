package com.technokratos.exceptions.already_exist;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class AlreadyExistHhException extends HhServiceException {
    public AlreadyExistHhException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
