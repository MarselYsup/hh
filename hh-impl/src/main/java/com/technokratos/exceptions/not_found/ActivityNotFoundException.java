package com.technokratos.exceptions.not_found;

public class ActivityNotFoundException extends NotFoundHhException{
    public ActivityNotFoundException() {
        super("Activity not found");
    }
}
