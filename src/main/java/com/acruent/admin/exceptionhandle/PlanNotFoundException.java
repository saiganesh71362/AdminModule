package com.acruent.admin.exceptionhandle;

public class PlanNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanNotFoundException(String message)
	{
		super(message);
	}
}
