package com.celfocus.training.util.exception;

public class RefactorigException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5107739719669628602L;

	public RefactorigException(String message, Exception cause) {
		super(message, cause);
	}

	public RefactorigException(Exception cause) {
		super("Application Error.", cause);
	}
}
