package com.soika.chess.figures;

import com.soika.chess.Board;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.IllegalMoveException;

public class Rook extends AbstractFigure {

	/**
	 * computes possible moves
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	  ┌───────────────┐
	  │  x            │
	  │  x            │
	  │  x            │
	  │x ♜ x x x x x x│
	  │  x            │
	  │  x            │
	  │  x            │
	  │  x            │
	  └───────────────┘
	   B5
     * </code>
	 * 
	 * @throws IllegalMoveException
	 */

	public Rook(Board aboard, int line, int row) throws IllegalBoardException {
		super(aboard, line, row);
	}

	
	@Override
	public byte getFigureType() {
		return Board.ROOK_ME;
	}

	@Override
	public void computeMoves() {

		Board board = getBoard();
		int l = this.getLine();
		int r = this.getRow();

		try {

			/* Compute Lines... */
			if (l < 8) {
				for (int i = l + 1; i <= 8; i++) {
					// possible move
					if (board.getFigure(i, r) <= 0) {
						this.addMove(i, r);
					}
				}
			}
			if (l > 1) {
				for (int i = l - 1; i >= 1; i--) {
					// possible move
					if (board.getFigure(i, r) <= 0) {
						this.addMove(i, r);
					}
				}
			}
			
			/* Compute rows... */
			if (r < 8) {
				for (int i = r + 1; i <= 8; i++) {
					// possible move
					if (board.getFigure(l,i) <= 0) {
						this.addMove(l,i);
					}
				}
			}
			if (r > 1) {
				for (int i = r - 1; i >= 1; i--) {
					// possible move
					if (board.getFigure(l,i) <= 0) {
						this.addMove( l,i);
					}
				}
			}

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}


}
