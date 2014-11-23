package com.soika.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	private final static Logger logger = Logger
			.getLogger(Board.class.getName());

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

	public final static byte DIRECTION_WHITE = 0;
	public final static byte DIRECTION_BLACK = 1;

	byte[] setup = null;
	byte direction = 0;

	public Board(byte adirection) {
		direction = adirection;
		setup = new byte[64];
	}

	public void initNewGame() {
		try {

			// computer plays black?
			if (this.getDirection() == DIRECTION_BLACK) {
				placeFigure("A1", Board.ROOK_YOURS);

				placeFigure("H1", Board.ROOK_YOURS);
				placeFigure("A8", Board.ROOK_ME);
				placeFigure("H8", Board.ROOK_ME);

				placeFigure("B1", Board.KNIGHT_YOURS);
				placeFigure("G1", Board.KNIGHT_YOURS);
				placeFigure("B8", Board.KNIGHT_ME);
				placeFigure("G8", Board.KNIGHT_ME);

				placeFigure("C1", Board.BISHOP_YOURS);
				placeFigure("F1", Board.BISHOP_YOURS);
				placeFigure("C8", Board.BISHOP_ME);
				placeFigure("F8", Board.BISHOP_ME);

				placeFigure("D1", Board.QUEEN_YOURS);
				placeFigure("D8", Board.QUEEN_ME);

				placeFigure("E1", Board.KING_YOURS);
				placeFigure("E8", Board.KING_ME);

				placeFigure("A2", Board.PAWN_YOURS);
				placeFigure("B2", Board.PAWN_YOURS);
				placeFigure("C2", Board.PAWN_YOURS);
				placeFigure("D2", Board.PAWN_YOURS);
				placeFigure("E2", Board.PAWN_YOURS);
				placeFigure("F2", Board.PAWN_YOURS);
				placeFigure("G2", Board.PAWN_YOURS);
				placeFigure("H2", Board.PAWN_YOURS);

				placeFigure("A7", Board.PAWN_ME);
				placeFigure("B7", Board.PAWN_ME);
				placeFigure("C7", Board.PAWN_ME);
				placeFigure("D7", Board.PAWN_ME);
				placeFigure("E7", Board.PAWN_ME);
				placeFigure("F7", Board.PAWN_ME);
				placeFigure("G7", Board.PAWN_ME);
				placeFigure("H7", Board.PAWN_ME);
			} else {
				// Setup computer plays white

				placeFigure("A1", Board.ROOK_ME);

				placeFigure("H1", Board.ROOK_ME);
				placeFigure("A8", Board.ROOK_YOURS);
				placeFigure("H8", Board.ROOK_YOURS);

				placeFigure("B1", Board.KNIGHT_ME);
				placeFigure("G1", Board.KNIGHT_ME);
				placeFigure("B8", Board.KNIGHT_YOURS);
				placeFigure("G8", Board.KNIGHT_YOURS);

				placeFigure("C1", Board.BISHOP_ME);
				placeFigure("F1", Board.BISHOP_ME);
				placeFigure("C8", Board.BISHOP_YOURS);
				placeFigure("F8", Board.BISHOP_YOURS);

				placeFigure("D1", Board.QUEEN_ME);
				placeFigure("D8", Board.QUEEN_YOURS);

				placeFigure("E1", Board.KING_ME);
				placeFigure("E8", Board.KING_YOURS);

				placeFigure("A7", Board.PAWN_YOURS);
				placeFigure("B7", Board.PAWN_YOURS);
				placeFigure("C7", Board.PAWN_YOURS);
				placeFigure("D7", Board.PAWN_YOURS);
				placeFigure("E7", Board.PAWN_YOURS);
				placeFigure("F7", Board.PAWN_YOURS);
				placeFigure("G7", Board.PAWN_YOURS);
				placeFigure("H7", Board.PAWN_YOURS);

				placeFigure("A2", Board.PAWN_ME);
				placeFigure("B2", Board.PAWN_ME);
				placeFigure("C2", Board.PAWN_ME);
				placeFigure("D2", Board.PAWN_ME);
				placeFigure("E2", Board.PAWN_ME);
				placeFigure("F2", Board.PAWN_ME);
				placeFigure("G2", Board.PAWN_ME);
				placeFigure("H2", Board.PAWN_ME);

			}
		} catch (IllegalBoardException e) {
			logger.severe("Error init board!");
			e.printStackTrace();
		}
	}

	public byte getDirection() {
		return direction;
	}

	/**
	 * This method moves a figure form one field to another. No validation is
	 * performed here.
	 * 
	 * @param from
	 * @param to
	 * @throws IllegalBoardException
	 */
	public void move(byte from, byte to) {
		byte figure = setup[from];
		setup[from] = 0;
		setup[to] = figure;
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

		// no figure defined on given the field!
		throw new IllegalBoardException("createFigure failed! field "
				+ fieldIndex + " no defined");
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
				List<Byte> moves = figure.getMoves();
				for (byte move : moves) {
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
	 * This method computes all possible moves on the board
	 * 
	 * @return
	 * @throws IllegalBoardException
	 */
	public List<byte[]> getYoursMoveList() throws IllegalBoardException {
		List<byte[]> result = new ArrayList<byte[]>();

		// test all fields...
		for (byte i = 0; i < 64; i++) {
			byte figureType = setup[i];
			// my figure?
			if (figureType < 0) {
				Figure figure = createFigure(i);
				// add all moves into the move list....
				List<Byte> moves = figure.getMoves();
				for (byte move : moves) {
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

		if (field == null || field.length() != 2) {
			throw new IllegalBoardException("Illegal move " + field);
		}

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

	/**
	 * Helper method to test if a move (byte[2]) is contained in a given move
	 * list
	 * 
	 * @param moveList
	 * @param move
	 * @return
	 */
	public static boolean isValidMove(List<byte[]> moveList, int moveFrom,
			int moveTo) {
		byte[] move = new byte[2];
		move[0] = (byte) moveFrom;
		move[1] = (byte) moveTo;

		for (byte[] amove : moveList) {
			if (amove[0] == move[0] && amove[1] == move[1])
				return true;
		}
		return false;
	}
}
