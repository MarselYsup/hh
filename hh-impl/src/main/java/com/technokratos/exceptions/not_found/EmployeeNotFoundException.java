package com.technokratos.exceptions.not_found;

public class EmployeeNotFoundException extends NotFoundHhException {
    public EmployeeNotFoundException() {
        super("Employee not found");
    }
}
