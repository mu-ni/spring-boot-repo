package com.muni.test.exception;

public class OauthException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356473988836394939L;

	public OauthException(String errorMessage) {
		super(errorMessage);
	}
}
