package com.technokratos.exceptions.file;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class UnableProcessHhException extends HhServiceException {
    public UnableProcessHhException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
