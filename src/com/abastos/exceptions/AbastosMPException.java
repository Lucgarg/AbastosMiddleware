package com.abastos.exceptions;

public class AbastosMPException extends Exception{
	
	public AbastosMPException() {
		super();
	}
	
	public AbastosMPException(String message) {
		super(message);		
	}
	
	public AbastosMPException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public AbastosMPException( Throwable cause) {
		super(cause);		
	}	
}
