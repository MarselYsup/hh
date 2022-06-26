package com.technokratos.exceptions.bad_request;

public class LocationBadRequestException extends BadRequestHhException{
    public LocationBadRequestException() {
        super("Bad request for parsing location ");
    }
}
