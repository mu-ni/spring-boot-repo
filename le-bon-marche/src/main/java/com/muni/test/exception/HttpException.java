package com.muni.test.exception;

public class HttpException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5872138764238302777L;

	public HttpException(String errorMessage) {
		super(errorMessage);
	}

}
