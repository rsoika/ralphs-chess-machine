package com.soika.chess.exceptions;

public class RemisException extends Exception {

	public RemisException(String message, RemisException e) {
		super(message,e);
	}

	public RemisException() {
		super();
	}

	private static final long serialVersionUID = 1L;

}
