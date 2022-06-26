package com.technokratos.exceptions.not_found;

public class VacancyNotFoundException extends NotFoundHhException{
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}
