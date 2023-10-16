package com.moviebooking.auth.exception;

public class InvalidInputException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //helps jvm to identify state of object
	
	public InvalidInputException(String msg) {
		super(msg);
	}
	

}
