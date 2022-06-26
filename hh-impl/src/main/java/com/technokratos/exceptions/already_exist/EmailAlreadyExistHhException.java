package com.technokratos.exceptions.already_exist;

public class EmailAlreadyExistHhException extends AlreadyExistHhException{
    public EmailAlreadyExistHhException() {
        super("Email address already exists");
    }
}
