package com.soika.chess.figures;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;

public class Pawn extends AbstractFigure {

	/**
	 * computes possible moves
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	  ┌───────────────┐
	  │               │
	  │               │
	  │x x x          │
	  │  ♟            │
	  │          x x x│
	  │            x  │
	  │            ♟  │
	  │               │
	  └───────────────┘
	   B5
     * </code>
	 * 
	 * @throws IllegalBoardException
	 */

	public Pawn(Board aboard, int line, int row,byte figureType) throws IllegalBoardException {
		super(aboard, line, row,figureType);
	}



	@Override
	public void computeMoves() {

		Board board = getBoard();
		int l = this.getLine();
		int r = this.getRow();

		try {

			/* Compute forward move... */
			if (r == 2) {
				// long move
				if (board.getFigure(l, r + 1) == 0 && board.getFigure(l, r + 2) == 0) {
					this.addMove(l, r + 2);
				}
			}
			if (r >= 2 && r < 8) {
				// possible move
				if (board.getFigure(l, r + 1) == 0) {
					this.addMove(l, r + 1);
				}
			}

			/* compute hits */
			// possible move
			if (l < 8 && board.getFigure(l + 1, r + 1) < 0) {
				this.addMove(l + 1, r + 1);
			}
			if (l > 1 && board.getFigure(l - 1, r + 1) < 0) {
				this.addMove(l - 1, r + 1);
			}

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

}
