package com.owtchallenge.exception;

public class ContactDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 4193142126970785707L;

	public ContactDuplicatedException() {
	    super("An identical contact already exist");
	}

}
