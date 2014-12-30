package com.soika.chess;

import java.util.Scanner;
import java.util.logging.Logger;

import com.soika.chess.exceptions.CheckMateException;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.RemisException;

public class Game {
	private static Game instance = null;
	Board board;
	static Scanner userInput;
	private final static Logger logger = Logger.getLogger(Game.class.getName());

	public static void main(String[] args) {
		logger.fine("Game init");

		// set log level
		// Analyzer.logger.setLevel(Level.FINE);

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

		Printer.print("*************************", Printer.LOGLEVEL_INFO);
		Printer.print("* Ralphs Chess Maschine *", Printer.LOGLEVEL_INFO);
		Printer.print("* V0.1.0                *", Printer.LOGLEVEL_INFO);
		Printer.print("*************************", Printer.LOGLEVEL_INFO);

		userInput = new Scanner(System.in);

		board = new Board(Board.DIRECTION_BLACK);
		Printer.print("Setup the board...", Printer.LOGLEVEL_INFO);

		board.initNewGame();
		Printer.print("Lets play...", Printer.LOGLEVEL_INFO);
		Printer.printBoard(board, Printer.LOGLEVEL_INFO);

	}

	public void play() {

		ChessMashine chessMashine = new ChessMashine();
		chessMashine.setAnalyzingDepth(2);
		while (true) {
			System.out.print("Please enter your move : ");
			String sMove = userInput.nextLine();

			// quit?
			if (sMove.toLowerCase().startsWith("q"))
				break;

			// Loglevel?
			if (sMove.toLowerCase().equals("l0")) {
				Printer.logLevel = Printer.LOGLEVEL_INFO;
				Printer.print("Changed Loglevel to INFO", Printer.LOGLEVEL_INFO);
				continue;
			}
			if (sMove.toLowerCase().equals("l1")) {
				Printer.logLevel = Printer.LOGLEVEL_FINE;
				Printer.print("Changed Loglevel to FINE", Printer.LOGLEVEL_FINE);
				continue;
			}
			if (sMove.toLowerCase().equals("l2")) {
				Printer.logLevel = Printer.LOGLEVEL_FINEST;
				Printer.print("Changed Loglevel to FINEST",
						Printer.LOGLEVEL_FINEST);
				continue;
			}

			// print board?
			if (sMove.toLowerCase().startsWith("p")) {
				Printer.printBoard(board, Printer.LOGLEVEL_INFO);
				continue;
			}

			// validate move
			if (sMove.length() > 4) {
				Printer.print("Illegal move!", Printer.LOGLEVEL_INFO);
				continue;
			}

			// evaluate move
			byte moveFrom;
			byte moveTo;
			try {
				moveFrom = Board.getFieldIndex(sMove.substring(0, 2));
				moveTo = Board.getFieldIndex(sMove.substring(2));
				if (!Board.isValidMove(board.getMoveList(false), moveFrom,
						moveTo)) {
					Printer.print("Illegal move!", Printer.LOGLEVEL_INFO);
					continue;
				}

			} catch (IllegalBoardException e) {
				Printer.print("Illegal move! (" + e.getMessage() + ")",
						Printer.LOGLEVEL_INFO);
				// logger.severe(e.toString());
				continue;
			}

			board.move(moveFrom, moveTo);

			// now computer moves...
			try {
				Analyzer myAnalyzedMove;
				try {
					chessMashine.start(board);

					try {
						Printer.print("let me think just a second....",
								Printer.LOGLEVEL_INFO);
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					myAnalyzedMove = chessMashine.stop();

					board.move(myAnalyzedMove.getMove()[0],
							myAnalyzedMove.getMove()[1]);
					Printer.printBoard(board, Printer.LOGLEVEL_INFO);
					Printer.print(
							"               My move : "
									+ Board.moveToString(myAnalyzedMove
											.getMove()) + "  (Rating="
									+ myAnalyzedMove.getResult() + " Depth="
									+ myAnalyzedMove.depth + ")",
							Printer.LOGLEVEL_INFO);

				} catch (RemisException e) {
					// game finished!
					Printer.printBoard(board, Printer.LOGLEVEL_INFO);
					Printer.print("Remis ! - no more moves possible! ",
							Printer.LOGLEVEL_INFO);
					break;

				} catch (CheckMateException e) {
					// game finished!
					Printer.printBoard(board, Printer.LOGLEVEL_INFO);
					Printer.print("Check mate! - You won!!! ",
							Printer.LOGLEVEL_INFO);
					break;
				}

			} catch (IllegalBoardException e) {
				Printer.print("GameOver! (" + e.getMessage() + ")",
						Printer.LOGLEVEL_INFO);
				break;
			}

		}
		Printer.print("Thanks for palying - Goodby...", Printer.LOGLEVEL_INFO);
	}

}
