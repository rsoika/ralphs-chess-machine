package com.soika.chess.exceptions;

public class CheckMateException extends Exception {

	public CheckMateException(String message, CheckMateException e) {
		super(message,e);
	}

	public CheckMateException() {
		super();
	}

	private static final long serialVersionUID = 1L;

}
