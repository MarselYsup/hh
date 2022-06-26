package com.technokratos.exceptions.token;

public class SignatureTokenException extends TokenHhException {
    public SignatureTokenException() {
        super("Invalid JWT signature");
    }
}
