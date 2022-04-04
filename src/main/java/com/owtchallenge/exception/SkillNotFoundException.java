package com.owtchallenge.exception;

public class SkillNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7206080349090123072L;

	public SkillNotFoundException(Long id) {
	    super("Could not find skill " + id);
	}

}
