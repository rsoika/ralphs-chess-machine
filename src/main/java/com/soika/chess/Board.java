package com.soika.chess;

import java.util.ArrayList;
import java.util.List;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.IllegalMoveException;
import com.soika.chess.figures.Bishop;
import com.soika.chess.figures.Figure;
import com.soika.chess.figures.King;
import com.soika.chess.figures.Knight;
import com.soika.chess.figures.Pawn;
import com.soika.chess.figures.Queen;
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
		try {
			setup[getFieldIndex(field)] = figure;
		} catch (IllegalBoardException e) {
			throw new IllegalBoardException("Illegal move " + field + ":"
					+ figure, e);
		}

	}

	public byte getFigure(String field) throws IllegalBoardException {
		byte fieldIndex = getFieldIndex(field);
		return setup[fieldIndex];
	}

	public byte getFigure(int line, int row) throws IllegalBoardException {
		return setup[getFieldIndex(line, row)];
	}

	public byte getFigure(byte fieldIndex) {
		return setup[fieldIndex];
	}

	/**
	 * Creates an instance of a figure class form the specified field
	 * 
	 * @param field
	 * @return
	 * @throws IllegalBoardException
	 */
	public Figure createFigure(String field) throws IllegalBoardException {
		// convert field string
		return createFigure(getFieldIndex(field));
	}

	/**
	 * Creates an instance of a figure class form the specified field
	 * 
	 * @param field
	 * @return
	 * @throws IllegalBoardException
	 */
	public Figure createFigure(int line, int row) throws IllegalBoardException {
		// convert field string
		return createFigure(getFieldIndex(line, row));
	}

	/**
	 * Creates an instance of a figure class form the specified field
	 * 
	 * @param field
	 * @return
	 * @throws IllegalBoardException
	 */
	public Figure createFigure(byte fieldIndex) throws IllegalBoardException {

		byte figure = this.getFigure(fieldIndex);
		if (figure == Board.PAWN_ME || figure == Board.PAWN_YOURS) {
			return new Pawn(this, fieldIndex, figure);
		}
		if (figure == Board.ROOK_ME || figure == Board.ROOK_YOURS) {
			return new Rook(this, fieldIndex, figure);
		}
		if (figure == Board.BISHOP_ME || figure == Board.BISHOP_YOURS) {
			return new Bishop(this, fieldIndex, figure);
		}
		if (figure == Board.KNIGHT_ME || figure == Board.KNIGHT_YOURS) {
			return new Knight(this, fieldIndex, figure);
		}
		if (figure == Board.QUEEN_ME || figure == Board.QUEEN_YOURS) {
			return new Queen(this, fieldIndex, figure);
		}
		if (figure == Board.KING_ME || figure == Board.KING_YOURS) {
			return new King(this, fieldIndex, figure);
		}

		return null;
	}

	/**
	 * This method computes all possible moves on the board
	 * 
	 * @return
	 * @throws IllegalBoardException
	 */
	public List<byte[]> getMyMoveList() throws IllegalBoardException {
		List<byte[]> result = new ArrayList<byte[]>();

		// test all fields...
		for (byte i = 0; i < 64; i++) {
			byte figureType = setup[i];
			// my figure?
			if (figureType > 0) {
				Figure figure = createFigure(i);
				// add all moves into the move list....
				for (byte move : figure.getMoves()) {
					byte[] amove = new byte[2];
					amove[0] = i;
					amove[1] = move;
					result.add(amove);
				}
			}
		}

		return result;
	}

	/**
	 * Static method to convert field definition into byte
	 * 
	 * @param field
	 * @return
	 * @throws IllegalMoveException
	 * @throws IllegalBoardException
	 */
	public static byte getFieldIndex(String field) throws IllegalBoardException {
		// convert field string
		field = field.toLowerCase();
		int line = field.charAt(0) - 'a' + 1;
		int row = field.charAt(1) - '1' + 1;

		return getFieldIndex(line, row);
	}

	/**
	 * Static method to convert field definition into byte
	 * 
	 * @param field
	 * @return
	 * @throws IllegalMoveException
	 */
	public static byte getFieldIndex(int line, int row)
			throws IllegalBoardException {
		// validate field
		if (line < 1 || line > 8 || row < 1 || row > 8) {
			throw new IllegalBoardException();
		}
		line--;
		row--;
		return (byte) (line + (row * 8));
	}

	/**
	 * Converts the fieldindex (0-63) into the coresponding line component
	 * 
	 * @param index
	 * @return
	 */
	public static byte lineFromIndex(byte index) {
		return (byte) (index - ((index / 8) * 8) + 1);
	}

	/**
	 * Converts the fieldindex (0-63) into the coresponding row component
	 * 
	 * @param index
	 * @return
	 */
	public static byte rowFromIndex(byte index) {
		return (byte) ((index / 8) + 1);
	}
}
