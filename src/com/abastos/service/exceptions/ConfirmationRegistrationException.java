package com.abastos.service.exceptions;

public class ConfirmationRegistrationException extends ServiceException{

	public ConfirmationRegistrationException() {
		// TODO Auto-generated constructor stub
	}
	public ConfirmationRegistrationException(String message) {
		super(message);		
	}
	
	public ConfirmationRegistrationException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public ConfirmationRegistrationException( Throwable cause) {
		super(cause);		
	}
}
