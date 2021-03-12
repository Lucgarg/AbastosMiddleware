package com.abastos.service.exceptions;

public class LimitCreationException extends ServiceException{

	public LimitCreationException() {
		super();
	}
	
	public LimitCreationException(String message) {
		super(message);		
	}
	
	public LimitCreationException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public LimitCreationException( Throwable cause) {
		super(cause);		
	}
}
