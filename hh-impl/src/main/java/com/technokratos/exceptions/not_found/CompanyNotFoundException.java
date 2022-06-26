package com.technokratos.exceptions.not_found;

public class CompanyNotFoundException extends NotFoundHhException{
    public CompanyNotFoundException() {
        super("Company not found");
    }
}
