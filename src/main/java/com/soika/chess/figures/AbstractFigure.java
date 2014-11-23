package com.soika.chess.figures;

import java.util.ArrayList;
import java.util.List;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.IllegalMoveException;

/**
 * Abstract Figure class. Provides general move list for a figure. A figure is
 * always bound to a board. The board defines the basis for the move list. This
 * class is extended by the concrete figure classes.
 * 
 * 
 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	  ┌───────────────┐
	  │               │
	  │               │
	  │               │
	  │               │
	  │               │
	  │               │
	  │               │
	  │               │
	  └───────────────┘
 * </code>
 * 
 * @author rsoika
 *
 */
public abstract class AbstractFigure implements Figure {

	Board board;
	byte figureType;
	List<Byte> moves;
	byte line, row;

	public AbstractFigure() {
		super();
	}

	public AbstractFigure(Board aboard, byte fieldIndex, byte figureType)
			throws IllegalBoardException {
		super();
		// validate params
		if (aboard.getFigure(fieldIndex) != figureType) {
			throw new IllegalBoardException();
		}

		this.row = Board.rowFromIndex(fieldIndex);
		this.line = Board.lineFromIndex(fieldIndex);
		this.figureType = figureType;

		reset(aboard);

	}

	public Board getBoard() {
		return board;
	}

	/**
	 * Abstract method to be overwritten be Figure classes. The method returns
	 * the figure type
	 * 
	 * @return
	 */
	public byte getFigureType() {
		return figureType;
	}

	public byte getLine() {
		return line;
	}

	public byte getRow() {
		return row;
	}

	/**
	 * reset the move list
	 */
	public void reset(Board board) {
		this.board = board;
		moves = new ArrayList<Byte>();
		computeMoves();
	}

	/**
	 * returns the list of moves
	 * 
	 * @return
	 */
	@Override
	public List<Byte> getMoves() {
		return moves;
	}

	/**
	 * adds a new move into the movelist
	 * 
	 * @param line
	 * @param row
	 * @throws IllegalMoveException
	 * @throws IllegalBoardException
	 */
	public void addMove(int line, int row) throws IllegalBoardException {

		byte mov = Board.getFieldIndex(line, row);
		if (!moves.contains(mov))
			moves.add(mov);
	}

	/**
	 * removes a exiting move from the move list
	 * 
	 * @param line
	 * @param row
	 * @throws IllegalMoveException
	 */
	public void removeMove(int line, int row) throws IllegalMoveException {
		// validate field
		if (line < 1 || line > 8 || row < 1 || row > 8) {
			throw new IllegalMoveException();
		}
		byte mov = (byte) (line * row);
		if (moves.contains(mov))
			moves.remove(mov);
	}

	/**
	 * This method returns true if a given FigureType is an opposite figure to
	 * this FigureType
	 * 
	 * @return
	 */
	public boolean isOpponent(byte afigureType) {
		if (figureType > 0)
			return (afigureType < 0);
		else
			return (afigureType > 0);
	}
	
	/**
	 * This method returns true if a given FigureType is empty or an opposite figure to
	 * this FigureType
	 * 
	 * @return
	 */
	public boolean isOpponentOrEmpty(byte afigureType) {
		if (afigureType == 0)
			return true;
		if (figureType > 0)
			return (afigureType < 0);
		else
			return (afigureType > 0);
	}

	/**
	 * This method returns true if a given FigureType is a leaguer figure to
	 * this FigureType
	 * 
	 * @return
	 */
	public boolean isLeaguer(byte afigureType) {
		if (figureType > 0)
			return (afigureType > 0);
		else
			return (afigureType < 0);
	}

	/**
	 * This method returns true if a given FigureType is empty or a leaguer
	 * figure to this FigureType
	 * 
	 * @return
	 */
	public boolean isLeaguerOrEmpty(byte afigureType) {
		if (afigureType == 0)
			return true;
		if (figureType > 0)
			return (afigureType > 0);
		else
			return (afigureType < 0);
	}

}
