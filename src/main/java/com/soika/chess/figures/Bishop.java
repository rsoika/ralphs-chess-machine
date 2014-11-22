package com.soika.chess.figures;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;

public class Bishop extends AbstractFigure {

	/**
	 * computes possible moves
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	  ┌───────────────┐
	  │        x      │
	  │      x        │
	  │x   x          │
	  │  ♝            │
	  │x  x           │
	  │     x         │
	  │       x       │
	  │         x     │
	  └───────────────┘
	   B5
     * </code>
	 * 
	 * @throws IllegalBoardException
	 */

	public Bishop(Board aboard, int line, int row, byte figureType)
			throws IllegalBoardException {
		super(aboard, line, row, figureType);
	}

	@Override
	public void computeMoves() {

		Board board = getBoard();
		int l;
		int r;

		try {

			/* Compute left up... */
			r = this.getRow();
			l = this.getLine();
			if (l > 1 && r < 8) {
				while (true) {
					l--;
					r++;
					if (l < 1 || r > 8)
						break;
					// possible move
					if (board.getFigure(l, r) <= 0)
						this.addMove(l, r);
					// blocked?
					if (board.getFigure(l, r) != 0)
						break;
				}
			}

			/* Compute right up... */
			r = this.getRow();
			l = this.getLine();
			if (l < 8 && r < 8) {
				while (true) {
					l++;
					r++;
					if (l > 8 || r > 8)
						break;
					// possible move
					if (board.getFigure(l, r) <= 0)
						this.addMove(l, r);

					// blocked?
					if (board.getFigure(l, r) != 0)
						break;
					
				}
			}
			
			
			/* Compute left down... */
			r = this.getRow();
			l = this.getLine();
			if (l > 1 && r > 1) {
				while (true) {
					l--;
					r--;
					if (l < 1 || r <1)
						break;
					// possible move
					if (board.getFigure(l, r) <= 0)
						this.addMove(l, r);
					// blocked?
					if (board.getFigure(l, r) != 0)
						break;
				}
			}
			
			
			
			
			/* Compute right down... */
			r = this.getRow();
			l = this.getLine();
			if (l <8 && r > 1) {
				while (true) {
					l++;
					r--;
					if (l > 8 || r <1)
						break;
					// possible move
					if (board.getFigure(l, r) <= 0)
						this.addMove(l, r);
					// blocked?
					if (board.getFigure(l, r) != 0)
						break;
				}
			}

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

}
