package com.abastos.service.exceptions;

public class UserNotFoundException extends ServiceException{

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);		
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public UserNotFoundException( Throwable cause) {
		super(cause);		
	}
}
