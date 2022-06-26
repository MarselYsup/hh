package com.technokratos.exceptions.token;

public class InvalidTokenException extends TokenHhException {
    public InvalidTokenException() {
        super("JWT was not correctly constructed");
    }
}
