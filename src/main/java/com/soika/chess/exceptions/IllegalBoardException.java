package com.soika.chess.exceptions;

public class IllegalBoardException extends Exception {

	public IllegalBoardException(String message, IllegalBoardException e) {
		super(message,e);
	}

	public IllegalBoardException() {
		super();
	}

	private static final long serialVersionUID = 1L;

}
