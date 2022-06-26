package com.technokratos.exceptions.file;

public class FileUnableProcessException extends UnableProcessHhException {
    public FileUnableProcessException(String filename){
        super(String.format("Cannot process file with name %s", filename));
    }
}
