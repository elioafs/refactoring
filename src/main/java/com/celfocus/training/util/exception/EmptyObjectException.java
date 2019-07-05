package com.celfocus.training.util.exception;

public class EmptyObjectException extends RefactorigException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4704730573035663713L;

	public EmptyObjectException(Exception cause, String param) {
		super("Object  " + param + " is Null", cause);
	}
}
