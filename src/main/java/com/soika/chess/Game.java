package com.soika.chess;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import com.soika.chess.exceptions.IllegalBoardException;

public class Game {
	private static Game instance = null;
	Board board;
	static Scanner userInput;
	private final static Logger logger = Logger.getLogger(Game.class.getName());

	public static void main(String[] args) {

		Game tmp = Game.getInstance();
		tmp.init();
		tmp.play();
	}

	/* Static 'instance' method */
	public static Game getInstance() {
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return instance;
	}

	/**
	 * Setup a new game (comp is black)
	 */
	public void init() {

		print("*************************");
		print("* Ralphs Chess Maschine *");
		print("* V0.0.1                *");
		print("*************************");

		userInput = new Scanner(System.in);

		board = new Board(Board.DIRECTION_BLACK);
		print("Setup the board...");

		board.initNewGame();
		print("Lets play...");

	}

	public void play() {
		while (true) {
			System.out.print("Please enter your move : ");
			String sMove = userInput.nextLine();

			// quit?
			if (sMove.toLowerCase().startsWith("q"))
				break;

			// print board?
			if (sMove.toLowerCase().startsWith("p")) {
				printBoard();
				continue;
			}

			// validate move
			if (sMove.length() > 4) {
				print("Illegal move!");
				continue;
			}

			// evaluate move
			byte moveFrom;
			byte moveTo;
			try {
				moveFrom = board.getFieldIndex(sMove.substring(0, 2));
				moveTo = board.getFieldIndex(sMove.substring(2));
				if (!Board.isValidMove(board.getYoursMoveList(), moveFrom,
						moveTo)) {
					print("Illegal move!");
					continue;
				}

			} catch (IllegalBoardException e) {
				print("Illegal move! (" + e.getMessage() + ")");
				// logger.severe(e.toString());
				continue;
			}

			// print("Your move : " + sMove + "("+moveFrom + "-"+moveTo+")");

			board.move(moveFrom, moveTo);

			// now computer moves...
			try {
				long l = System.currentTimeMillis();
				List<byte[]> myMoves = board.getMyMoveList();
				print("....." + myMoves.size() + " moves found in : "
						+ (System.currentTimeMillis() - l) + "ms");
				if (myMoves.size() == 0) {
					print("You won!!! ");
					break;
				}

				// randomize a move....
				int moveNumber = randInt(0, myMoves.size() - 1);

				byte[] myMove = myMoves.get(moveNumber);

			
				print("               My move : " + Board.moveToString(myMove));
				board.move(myMove[0], myMove[1]);
			} catch (IllegalBoardException e) {
				print("GameOver! (" + e.getMessage() + ")");
				break;
			}

		}
		print("Goodby...");
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
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
	private void printBoard() {

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			System.out.println("│");
		}

		print("        └───────────────┘");
	}

	private static void print(String message) {
		// logger.info(message);
		System.out.println(message);
	}
}
