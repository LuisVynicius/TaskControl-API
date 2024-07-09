package com.mevy.taskcontrolapi.services.exceptions;

public class DatabaseIntegrityException extends RuntimeException{

    public DatabaseIntegrityException(String message) {
        super(message);
    }

}
