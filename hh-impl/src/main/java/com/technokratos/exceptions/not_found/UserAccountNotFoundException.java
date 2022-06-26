package com.technokratos.exceptions.not_found;

public class UserAccountNotFoundException extends NotFoundHhException {
    public UserAccountNotFoundException() {
        super("User not found");
    }
}
