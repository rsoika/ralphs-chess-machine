package com.soika.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		logger.fine("Game init");
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

		Printer.print("*************************");
		Printer.print("* Ralphs Chess Maschine *");
		Printer.print("* V0.0.1                *");
		Printer.print("*************************");

		userInput = new Scanner(System.in);

		board = new Board(Board.DIRECTION_BLACK);
		Printer.print("Setup the board...");

		board.initNewGame();
		Printer.print("Lets play...");

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
				Printer.printBoard(board);
				continue;
			}

			// validate move
			if (sMove.length() > 4) {
				Printer.print("Illegal move!");
				continue;
			}

			// evaluate move
			byte moveFrom;
			byte moveTo;
			try {
				moveFrom = Board.getFieldIndex(sMove.substring(0, 2));
				moveTo = Board.getFieldIndex(sMove.substring(2));
				if (!Board.isValidMove(board.getYoursMoveList(), moveFrom,
						moveTo)) {
					Printer.print("Illegal move!");
					continue;
				}

			} catch (IllegalBoardException e) {
				Printer.print("Illegal move! (" + e.getMessage() + ")");
				// logger.severe(e.toString());
				continue;
			}

			// print("Your move : " + sMove + "("+moveFrom + "-"+moveTo+")");

			board.move(moveFrom, moveTo);

			// now computer moves...
			try {
				long l = System.currentTimeMillis();
				List<byte[]> myMoves = board.getMyMoveList();
				Printer.print("....." + myMoves.size() + " moves found in : "
						+ (System.currentTimeMillis() - l) + "ms");
				if (myMoves.size() == 0) {
					Printer.print("You won!!! ");
					break;
				}
				
				 l = System.currentTimeMillis();
				 List<Analyzer> bestMoves= analyzeMoves(myMoves);

				 Printer.print("....." + bestMoves.size() + " usefull moves analysed in : "
							+ (System.currentTimeMillis() - l) + "ms");
				 
				// randomize a move....
				int moveNumber = randInt(0, bestMoves.size() - 1);

				Analyzer myAnalyzedMove = bestMoves.get(moveNumber);
				
				

				Printer.print("               My move : "
						+ Board.moveToString(myAnalyzedMove.getMove()));
				board.move(myAnalyzedMove.getMove()[0], myAnalyzedMove.getMove()[1]);
			} catch (IllegalBoardException e) {
				Printer.print("GameOver! (" + e.getMessage() + ")");
				break;
			}

		}
		Printer.print("Thanks for palying - Goodby...");
	}

	/**
	 * This method analyzes a list of moves and returns the list with the best
	 * moves (worst moves are removed)
	 * 
	 * @param moves
	 * @throws IllegalBoardException
	 */
	public List<Analyzer> analyzeMoves(List<byte[]> moves) throws IllegalBoardException {
		Printer.print("Start analyizing.....");
		List<Analyzer> analyzerList = new ArrayList<Analyzer>();
		List<Analyzer> bestMoves = new ArrayList<Analyzer>();

		for (byte[] move : moves) {
			analyzerList.add( new Analyzer(board, move));

		}
		
		// sort moves by worst case
		Collections.sort(analyzerList, new AnalyzerComparator());
		Analyzer bestAnalyzer=null;
		for (Analyzer a: analyzerList) {
			if (bestAnalyzer==null || bestAnalyzer.getResult()>(a.getResult()-1)) {
				bestMoves.add(a);
				bestAnalyzer=a;
			} else {
				// not more good moves
				break;
			}
		}
		
		
		
		return bestMoves;
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

	
	public class AnalyzerComparator implements Comparator<Analyzer> {

	    public int compare(Analyzer a1, Analyzer a2){
	    	

	    	
	       return a2.getResult() - a1.getResult();
	    }
	}
	
	
}
