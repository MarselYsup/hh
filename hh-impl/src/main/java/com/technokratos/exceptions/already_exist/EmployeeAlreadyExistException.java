package com.technokratos.exceptions.already_exist;

public class EmployeeAlreadyExistException extends AlreadyExistHhException {
    public EmployeeAlreadyExistException() {
        super("Employee already exist");
    }
}
