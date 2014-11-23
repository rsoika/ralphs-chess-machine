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

	public Pawn(Board aboard, byte fieldIndex, byte figureType)
			throws IllegalBoardException {
		super(aboard, fieldIndex, figureType);
	}

	@Override
	public void computeMoves() {

		Board board = getBoard();
		int l = this.getLine();
		int r = this.getRow();

		try {

			// Direction up....
			if ((board.getDirection() == Board.DIRECTION_WHITE && figureType > 0)
					|| (board.getDirection() == Board.DIRECTION_BLACK && figureType < 0)) {
				/* Compute forward move... */
				if (r == 2) {
					// long move
					if (board.getFigure(l, r + 1) == 0
							&& board.getFigure(l, r + 2) == 0) {
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
				if (r < 8) {
					if (l < 8 && isOpponent(board.getFigure(l + 1, r + 1))) {
						this.addMove(l + 1, r + 1);
					}
					if (l > 1 && isOpponent(board.getFigure(l - 1, r + 1))) {
						this.addMove(l - 1, r + 1);
					}
				}
			} else {
				// compute pawn moves for black

				/* Compute forward move... */
				if (r == 7) {
					// long move
					if (board.getFigure(l, r - 1) == 0
							&& board.getFigure(l, r - 2) == 0) {
						this.addMove(l, r - 2);
					}
				}
				if (r <= 7 && r > 1) {
					// possible move
					if (board.getFigure(l, r - 1) == 0) {
						this.addMove(l, r - 1);
					}
				}

				/* compute hits */
				// possible move
				if (r > 1) {
					if (l > 8 && isOpponent(board.getFigure(l + 1, r - 1))) {
						this.addMove(l + 1, r - 1);
					}
					if (l > 1 && isOpponent(board.getFigure(l - 1, r - 1))) {
						this.addMove(l - 1, r - 1);
					}

				}

			}
		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

}
