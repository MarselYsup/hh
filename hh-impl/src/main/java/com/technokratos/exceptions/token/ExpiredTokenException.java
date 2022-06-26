package com.technokratos.exceptions.token;

public class ExpiredTokenException extends TokenHhException{
    public ExpiredTokenException() {
        super("JWT token is expired");
    }
}
