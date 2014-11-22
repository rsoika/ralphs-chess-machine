package com.soika.chess;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.IllegalMoveException;
import com.soika.chess.figures.AbstractFigure;
import com.soika.chess.figures.Bishop;
import com.soika.chess.figures.Pawn;
import com.soika.chess.figures.Rook;

/**
 * Definition of a chess board
 * 
 * http://www.wikihow.com/Play-Chess-for-Beginners
 * 
 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	  ┌───────────────┐
	  │♜ ♞ ♝ ♚ ♛ ♝ ♞ ♜│
	  │♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│
	  │               │
	  │               │
	  │               │
	  │               │
	  │♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙│
	  │♖ ♘ ♗ ♔ ♕ ♗ ♘ ♖│
	  └───────────────┘
 * </code>
 * 
 * @author rsoika
 *
 */
public class Board {

	public final static byte FREE = 0;

	public final static byte PAWN_ME = 1;
	public final static byte BISHOP_ME = 2;
	public final static byte KNIGHT_ME = 3;
	public final static byte ROOK_ME = 4;
	public final static byte QUEEN_ME = 5;
	public final static byte KING_ME = 6;

	public final static byte PAWN_YOURS = -1;
	public final static byte BISHOP_YOURS = -2;
	public final static byte KNIGHT_YOURS = -3;
	public final static byte ROOK_YOURS = -4;
	public final static byte QUEEN_YOURS = -5;
	public final static byte KING_YOURS = -6;

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
	 * 
	 * @throws IllegalBoardException
	 */
	public void placeFigure(String field, byte figure)
			throws IllegalBoardException {

		// convert field stringg
		field = field.toLowerCase();
		int line = field.charAt(0) - 'a' + 1;
		int row = field.charAt(1) - '1' + 1;

		try {
			placeFigure(line, row, figure);
		} catch (IllegalBoardException e) {
			throw new IllegalBoardException("Illegal move " + field + ":"
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
	 * @throws IllegalBoardException 
	 */
	public void placeFigure(int line, int row, byte figure)
			throws  IllegalBoardException {

		// validate field
		if (line < 1 || line > 8 || row < 1 || row > 8) {
			throw new IllegalBoardException();
		}

		// validate figure
		if (figure < -6 || figure > 6) {
			throw new IllegalBoardException();
		}

		// set figure
		setup[getField(line, row)] = figure;
	}

	public byte getFigure(int line, int row) throws IllegalBoardException {
		byte field = getField(line, row);
		return setup[field];
	}

	public AbstractFigure createFigure(String field) throws IllegalBoardException  {
		// convert field string
		field = field.toLowerCase();
		int line = field.charAt(0) - 'a' + 1;
		int row = field.charAt(1) - '1' + 1;

		byte figure = this.getFigure(line, row);
		if (figure == Board.ROOK_ME || figure == Board.ROOK_YOURS) {
			return new Rook(this, line, row,figure);
		}
		if (figure == Board.PAWN_ME || figure == Board.PAWN_YOURS) {
			return new Pawn(this, line, row,figure);
		}
		if (figure == Board.BISHOP_ME || figure == Board.BISHOP_YOURS) {
			return new Bishop(this, line, row,figure);
		}


		return null;
	}

	/**
	 * Static method to convert field definition into byte
	 * 
	 * @param field
	 * @return
	 * @throws IllegalMoveException
	 * @throws IllegalBoardException 
	 */
	public static byte getField(String field) throws IllegalBoardException {

		// convert field string
		field = field.toLowerCase();
		int line = field.charAt(0) - 'a' + 1;
		int row = field.charAt(1) - '1' + 1;

		return getField(line, row);

	}

	/**
	 * Static method to convert field definition into byte
	 * 
	 * @param field
	 * @return
	 * @throws IllegalMoveException
	 */
	public static byte getField(int line, int row) throws IllegalBoardException {
		// validate field
		if (line < 1 || line > 8 || row < 1 || row > 8) {
			throw new IllegalBoardException();
		}

		line--;
		row--;
		return (byte) (line + (row * 8));

	}
}
