package com.damddos.exceptions;

public class NetworkException extends Exception{
	public NetworkException(String urlString, String string) {
        super();
    }
	
    public NetworkException(String errorMessage) {
        super("Network Problem: " + errorMessage);
    }

    public NetworkException(String urlString, String string, Exception e) {
        super(e);
    }

    public NetworkException(String errorMessage, Exception e) {
        super("Network Problem: " + errorMessage, e);
    }
}
