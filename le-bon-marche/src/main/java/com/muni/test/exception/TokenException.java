package com.muni.test.exception;

public class TokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8481907235200414913L;

	public TokenException(String errorMessage) {
		super(errorMessage);
	}
}
