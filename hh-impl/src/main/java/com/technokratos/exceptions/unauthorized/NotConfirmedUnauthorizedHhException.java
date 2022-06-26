package com.technokratos.exceptions.unauthorized;

public class NotConfirmedUnauthorizedHhException extends UnauthorizedHhException{
    public NotConfirmedUnauthorizedHhException() {
        super("User is not confirmed");
    }
}
