package com.abastos.service.exceptions;

public class MailException extends ServiceException{
	public MailException() {
		super();
	}
	
	public MailException(String message) {
		super(message);		
	}
	
	public MailException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public MailException( Throwable cause) {
		super(cause);		
	}	
}
