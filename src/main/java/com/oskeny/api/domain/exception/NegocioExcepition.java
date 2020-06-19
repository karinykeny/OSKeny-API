package com.oskeny.api.domain.exception;

public class NegocioExcepition extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NegocioExcepition(String message) {
		super(message);
	}

}
