package com.owtchallenge.exception;

public class ContactNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3288377318473583518L;

	public ContactNotFoundException(Long id) {
	    super("Could not find contact " + id);
	}

}
