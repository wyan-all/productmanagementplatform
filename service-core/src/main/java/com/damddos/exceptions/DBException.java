package com.damddos.exceptions;

public class DBException extends Exception{
    public DBException() {
        super();
    }

    public DBException(String errorMessage) {
        super("DB Problem: " + errorMessage);
    }

    public DBException(Exception e) {
        super(e);
    }

    public DBException(String errorMessage, Exception e) {
        super("DB Problem: " + errorMessage, e);
    }
    
}
