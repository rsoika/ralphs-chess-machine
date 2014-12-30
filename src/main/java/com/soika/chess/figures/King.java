package com.soika.chess.figures;

import java.util.List;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;

public class King extends AbstractFigure {

	/**
	 * computes possible moves
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│               │
	 7│               │
	 6│               │
	 5│x x x          │
	 4│x ♛ x          │
	 3│x x x          │
	 2│               │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   B4
     * </code>
	 * 
	 * @throws IllegalBoardException
	 */

	public King(Board aboard, byte fieldIndex, byte figureType)
			throws IllegalBoardException {
		super(aboard, fieldIndex, figureType);
	}

	/**
	 * For the king we need to check first all fields controlled by the
	 * opposite. These fields can not be taken into the movelist!
	 */
	@Override
	public void computeMoves() {

		Board board = getBoard();

		int l;
		int r;

		try {

			r = this.getRow();
			l = this.getLine();
			/* Compute upper row... */
			if (r < 8) {
				if (l > 1) {
					// possible move
					if (isOpponentOrEmpty(board.getFigure(l - 1, r + 1))) {
						this.addMove(l - 1, r + 1);
					}
				}
				if (l < 8) {
					// possible move
					if (isOpponentOrEmpty(board.getFigure(l + 1, r + 1))) {
						this.addMove(l + 1, r + 1);
					}
				}
				// possible move
				if (isOpponentOrEmpty(board.getFigure(l, r + 1))) {
					this.addMove(l, r + 1);
				}
			}

			/* Compute lower row... */
			if (r > 1) {
				if (l > 1) {
					// possible move
					if (isOpponentOrEmpty(board.getFigure(l - 1, r - 1))) {
						this.addMove(l - 1, r - 1);
					}
				}
				if (l < 8) {
					// possible move
					if (isOpponentOrEmpty(board.getFigure(l + 1, r - 1))) {
						this.addMove(l + 1, r - 1);
					}
				}
				// possible move
				if (isOpponentOrEmpty(board.getFigure(l, r - 1))) {
					this.addMove(l, r - 1);
				}
			}

			/* Compute left / right... */
			if (l > 1) {
				// possible move
				if (isOpponentOrEmpty(board.getFigure(l - 1, r))) {
					this.addMove(l - 1, r);
				}
			}
			if (l < 8) {
				// possible move
				if (isOpponentOrEmpty(board.getFigure(l + 1, r))) {
					this.addMove(l + 1, r);
				}
			}

		

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

}
