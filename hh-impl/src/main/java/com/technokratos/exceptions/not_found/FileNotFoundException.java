package com.technokratos.exceptions.not_found;

public class FileNotFoundException extends NotFoundHhException {
    public FileNotFoundException() {
        super("File not found");
    }
}
