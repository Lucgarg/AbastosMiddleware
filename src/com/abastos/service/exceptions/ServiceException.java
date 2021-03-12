package com.abastos.service.exceptions;

import com.abastos.exceptions.AbastosMPException;

public class ServiceException extends AbastosMPException{
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);		
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public ServiceException( Throwable cause) {
		super(cause);		
	}	
}
