package com.technokratos.exceptions.restricted_permission;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpStatus;

public class RestrictedPermissionHhException extends HhServiceException {
    public RestrictedPermissionHhException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
