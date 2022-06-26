package com.technokratos.exceptions.unauthorized;

public class UserAccountUnauthorizedException extends UnauthorizedHhException{
    public UserAccountUnauthorizedException() {
        super("User is unauthorized");
    }
}
