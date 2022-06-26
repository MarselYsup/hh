package com.technokratos.exceptions.not_found;

public class TokenNotFoundException extends NotFoundHhException {
    public TokenNotFoundException() {
        super("Token not found");
    }
}
