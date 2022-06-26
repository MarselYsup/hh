package com.technokratos.exceptions.token;

public class UnsupportedTokenException extends TokenHhException{
    public UnsupportedTokenException() {
        super("JWT token is unsupported");
    }
}
