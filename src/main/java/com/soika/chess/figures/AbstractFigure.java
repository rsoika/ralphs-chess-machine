package com.soika.chess.figures;

import java.util.ArrayList;
import java.util.List;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.IllegalMoveException;

/**
 * Abstract Figure class. Provides general move list for a figure. A figure is
 * always bound to a board. The board defines the basis for the move list.
 * This class is extended by the concrete figure classes.
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
 * @author rsoika
 *
 */
public abstract class AbstractFigure implements Figure {

	Board board;
	byte figureType;
	List<Byte> moves;
	byte line,row;

	public AbstractFigure() {
		super();
	}

	public AbstractFigure(Board aboard, int line, int row, byte figureType) throws IllegalBoardException {
		super();
		// validate params
		if (aboard.getFigure(line, row)!=figureType) {
			throw new IllegalBoardException();
		}
		
		this.row=(byte) row;
		this.line=(byte) line;
		this.figureType=figureType;

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
	public void addMove(int line, int row) throws  IllegalBoardException {
	
		byte mov = Board.getField(line,row);
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

}
