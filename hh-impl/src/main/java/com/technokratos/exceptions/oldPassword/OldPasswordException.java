package com.technokratos.exceptions.oldPassword;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class OldPasswordException extends HhServiceException {
    public OldPasswordException() {
        super(HttpStatus.BAD_REQUEST, "Old password was entered");
    }
}
