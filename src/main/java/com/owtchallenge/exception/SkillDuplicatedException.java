package com.owtchallenge.exception;

public class SkillDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 7021336125661053398L;

	public SkillDuplicatedException() {
	    super("An identical skill already exist");
	}

}
