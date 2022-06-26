package com.technokratos.exceptions.token;

public class EmptyTokenException extends TokenHhException{
    public EmptyTokenException() {
        super("JWT claims string is empty");
    }
}
