package com.soika.chess;

import java.util.logging.Level;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * prints a board setup
 * @author rsoika
 *
 */
public class Printer {
	static byte logLevel=0;
	
	public final static byte LOGLEVEL_INFO=0;
	public final static byte LOGLEVEL_FINE=1;
	public final static byte LOGLEVEL_FINEST=2;
	public final static byte LOGLEVEL_DEBUG=3;
	
	
	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(byte logLevel) {
		Printer.logLevel = logLevel;
		
		
	}

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
	public static void printBoard(Board board,byte alogLevel) {

		if (alogLevel>logLevel)
			return;
		
		System.out.println("         A B C D E F G H");
		System.out.println("        ┌───────────────┐");

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

		System.out.println("        └───────────────┘");
	}

	public static void print(String message,byte alogLevel) {
		if (alogLevel>logLevel)
			return;
		// logger.info(message);
		System.out.println(message);
	}
}
