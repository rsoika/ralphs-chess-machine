package com.soika.chess;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * prints a board setup
 * @author rsoika
 *
 */
public class Printer {
	Board board;

	/**
	 * prints the board
	 * 
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	    A B C D E F G H
	   ┌───────────────┐
	 8 │♜ ♞ ♝ ♚ ♛ ♝ ♞ ♜│
	 7 │♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│
	 6 │               │
	 5 │               │
	 4 │               │
	 3 │               │
	 2 │♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙│
	 1 │♖ ♘ ♗ ♔ ♕ ♗ ♘ ♖│
	   └───────────────┘
	 * </code>
	 * 
	 * @throws IllegalBoardException
	 */
	public static void printBoard(Board board) {

		print("         A B C D E F G H");
		print("        ┌───────────────┐");

		for (int row = 8; row > 0; row--) {

			System.out.print("      " + row + " │");
			for (int line = 1; line < 9; line++) {
				try {
					byte figure = board.getFigure(line, row);

					switch (figure) {
					case Board.ROOK_ME:
						System.out.print("♜");
						break;
					case Board.KNIGHT_ME:
						System.out.print("♞");
						break;
					case Board.BISHOP_ME:
						System.out.print("♝");
						break;
					case Board.QUEEN_ME:
						System.out.print("♛");
						break;
					case Board.KING_ME:
						System.out.print("♚");
						break;
					case Board.PAWN_ME:
						System.out.print("♟");
						break;
							
					case Board.ROOK_YOURS:
						System.out.print("♖");
						break;
					case Board.KNIGHT_YOURS:
						System.out.print("♘");
						break;
					case Board.BISHOP_YOURS:
						System.out.print("♗");
						break;
					case Board.QUEEN_YOURS:
						System.out.print("♕");
						break;
					case Board.KING_YOURS:
						System.out.print("♔");
						break;
					case Board.PAWN_YOURS:
						System.out.print("♙");
						break;

					default:
						System.out.print(" ");
					}

					if (line<8)
					System.out.print(" ");
				} catch (IllegalBoardException e) {
					e.printStackTrace();
				}

			}

			System.out.println("│");
		}

		print("        └───────────────┘");
	}

	public static void print(String message) {
		// logger.info(message);
		System.out.println(message);
	}
}
