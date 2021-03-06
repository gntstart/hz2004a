package com.hzjc.hz2004.exception;


public class ActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ActionException() {
		super();
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(Throwable cause) {
		super(cause);
	}
}
