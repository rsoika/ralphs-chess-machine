package com.soika.chess.figures;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;

public class Knight extends AbstractFigure {

	/**
	 * computes possible moves
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│               │
	 7│               │
	 6│  x   x        │
	 5│x       x      │
	 4│    ♞          │
	 3│x       x      │
	 2│  x   x        │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>
	 * 
	 * @throws IllegalBoardException
	 */

	public Knight(Board aboard, int line, int row, byte figureType)
			throws IllegalBoardException {
		super(aboard, line, row, figureType);
	}

	@Override
	public void computeMoves() {

		Board board = getBoard();
		int l;
		int r;

		try {
			r = this.getRow();
			l = this.getLine();
			/* Compute upper top-row... */
			if (r < 7) {
				if (l > 1) {
					// possible move
					if (board.getFigure(l - 1, r + 2) <= 0) {
						this.addMove(l - 1, r + 2);
					}
				}
				if (l < 8) {
					// possible move
					if (board.getFigure(l + 1, r + 2) <= 0) {
						this.addMove(l + 1, r + 2);
					}
				}
			}
			
			/* Compute upper low-row... */
			if (r < 8) {
				if (l > 2) {
					// possible move
					if (board.getFigure(l - 2, r + 1) <= 0) {
						this.addMove(l - 2, r + 1);
					}
				}
				if (l < 7) {
					// possible move
					if (board.getFigure(l + 2, r + 1) <= 0) {
						this.addMove(l + 2, r + 1);
					}
				}
			}
			
			/* Compute lower top-row... */
			if (r > 2) {
				if (l > 1) {
					// possible move
					if (board.getFigure(l - 1, r - 2) <= 0) {
						this.addMove(l - 1, r - 2);
					}
				}
				if (l < 8) {
					// possible move
					if (board.getFigure(l + 1, r - 2) <= 0) {
						this.addMove(l + 1, r - 2);
					}
				}
			}
			
			/* Compute lower low-row... */
			if (r > 1) {
				if (l > 2) {
					// possible move
					if (board.getFigure(l - 2, r - 1) <= 0) {
						this.addMove(l - 2, r - 1);
					}
				}
				if (l < 7) {
					// possible move
					if (board.getFigure(l + 2, r - 1) <= 0) {
						this.addMove(l + 2, r - 1);
					}
				}
			}

			
			
			
			
			
			
			
			
		
		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

}
