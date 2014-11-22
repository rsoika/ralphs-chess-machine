package com.soika.chess;

/**
 * Definition of a chess board
 * 
 * http://www.wikihow.com/Play-Chess-for-Beginners
 * 
 * 
 * @author rsoika
 *
 */
public class Board {

	final static byte FREE = 0;

	final static byte PAWN_ME = 1;
	final static byte BISHOP_ME = 2;
	final static byte KNIGHT_ME = 3;
	final static byte ROOK_ME = 4;
	final static byte QUEEN_ME = 5;
	final static byte KING_ME = 6;

	final static byte PAWN_YOURS = -1;
	final static byte BISHOP_YOURS = -2;
	final static byte KNIGHT_YOURS = -3;
	final static byte ROOK_YOURS = -4;
	final static byte QUEEN_YOURS = -5;
	final static byte KING_YOURS = -6;

	byte[] setup = null;

	public Board() {
		setup = new byte[64];
	}

	/**
	 * Places a figure on the board. The field is defined by a string by a char
	 * A-H and a number 1-8
	 * 
	 * call example: <br>
	 * <code>
	   		placeFigure('E3',PAWN_ME);
	 * </code>
	 */
	public void placeFigure(String field, byte figure)
			throws IllegalMoveException {

		// convert field stringg
		field = field.toLowerCase();
		int line = field.charAt(0) - 'a' + 1;
		int row = field.charAt(1) - '1' + 1;

		try {
			placeFigure(line, row, figure);
		} catch (IllegalMoveException e) {
			throw new IllegalMoveException("Illegal move " + field + ":"
					+ figure, e);
		}

	}

	/**
	 * places a figure on the field
	 * 
	 * @param line
	 *            1-8
	 * @param row
	 *            1-8
	 * @param figure
	 *            -6<6
	 * @throws IllegalMoveException
	 */
	public void placeFigure(int line, int row, byte figure)
			throws IllegalMoveException {

		// validate field
		if (line < 1 || line > 8 || row < 1 || row > 8) {
			throw new IllegalMoveException();
		}

		// validate figure
		if (figure < -6 || figure > 6) {
			throw new IllegalMoveException();
		}

		byte field = (byte) (row * line);

		// set figure
		setup[field - 1] = figure;
	}

}
