package com.abastos.service;

import com.abastos.exceptions.AbastosMPException;

public class DataException   extends AbastosMPException{
	
	public DataException() {
		super();
	}
	public DataException(Throwable cause) {
			super(cause);		
		}	
	public DataException(String message) {
		super(message);		
	}
	
	public DataException(String message, Throwable cause) {
		super(message, cause);		
	}
}
