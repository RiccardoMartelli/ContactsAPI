package com.owtchallenge.exception;

public class ContactAlreadyHasSkillException extends RuntimeException {

	private static final long serialVersionUID = 4205289269674670017L;

	public ContactAlreadyHasSkillException(String skillName,Long contactId) {
	    super("Contact "+contactId+" already has skill "+skillName);
	}

}
