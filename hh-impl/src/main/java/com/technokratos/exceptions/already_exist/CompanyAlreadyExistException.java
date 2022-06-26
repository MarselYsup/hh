package com.technokratos.exceptions.already_exist;

public class CompanyAlreadyExistException extends AlreadyExistHhException{
    public CompanyAlreadyExistException() {
        super("Company already exists");
    }
}
