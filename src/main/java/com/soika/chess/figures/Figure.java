package com.soika.chess.figures;

import java.util.List;

/**
 * Interface for figures. This interface defines a method to compute possible
 * moves and a getter to get the computes list of moves.
 * 
 * @see AbstractFigure
 * @author rsoika
 *
 */
public interface Figure {

	/**
	 * returns the list of moves
	 * 
	 * @return
	 */
	public List<Byte> getMoves();

	/**
	 * Abstract method to be overwritten be Figure classes. The method computes
	 * the list of possible moves on the given board
	 */
	public abstract void computeMoves();
}
