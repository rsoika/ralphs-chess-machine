package com.soika.chess;

public class IllegalMoveException extends Exception {

	public IllegalMoveException(String message, IllegalMoveException e) {
		super(message,e);
	}

	public IllegalMoveException() {
		super();
	}

	private static final long serialVersionUID = 1L;

}
