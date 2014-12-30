package com.soika.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * This class analysis a move for a given board and computes the worst case as a
 * result of that move.
 * 
 * The Analyzer creates a clone of a Boards setup. This internal setup can be
 * modified by the Analyzer to test different moves. There for it is possible to
 * run multiple analyzers parallel for one board.
 * 
 * The Analyzer computes a full-move. This means: given a possible computer-move
 * the Analyzer computes any possible reaction and rates the new situation. Each
 * Move becomes a rating which can be used for further analysis.
 * 
 * 
 * http://de.wikibooks.org/wiki/Java_Standard:_Threads
 * 
 * @author rsoika
 *
 */
public class Analyzer extends Thread { // implements Runnable

	public final static byte KING_LOST = -88;
	public final static byte KING_WON = 88;

	Board board;
	int result = KING_LOST;
	int startRating = 0;
	byte[] move;
	byte depth = 0;
	long countSituations = 0;

	List<byte[]> path = null;

	private int analyzingDepth = 1;

	/**
	 * Given a board and a move the analyzer compute all possible reactions and
	 * rates the move based on worst situation which can happen.
	 * 
	 * If one reaction move takes the king we can cancel the analyze process
	 * 
	 * @param aboard
	 * @param amove
	 * @throws IllegalBoardException
	 */
	public Analyzer(final Board aboard, byte[] amove, int analyzingDepth)
			throws IllegalBoardException {
		super();

		this.analyzingDepth = analyzingDepth;
		this.move = amove;
		result = 0;
		// paths = new ArrayList<Move[]>();

		// clone board...
		this.board = new Board(aboard.direction);
		this.board.setup = aboard.setup.clone();

		board.doMove(amove[0], amove[1]);

	}

	/**
	 * Isolated code for analyze process
	 * 
	 * @throws IllegalBoardException
	 */
	@Override
	public void run() {

		depth = 0;
		startRating = board.rateBoard();

		// now start computing all possible reactions and give each reaction a
		// rating

		while (depth < analyzingDepth) {

			if (Printer.logLevel >= Printer.LOGLEVEL_FINE) {

				Printer.print("Analyzer " + Board.moveToString(this.move)
						+ " started depth=" + depth + " startrating="
						+ startRating, Printer.LOGLEVEL_FINE);
			}

			try {

				path = new ArrayList<byte[]>();
				path.add(move);

				result = dummyRun(true, depth, startRating);

				if (Printer.logLevel >= Printer.LOGLEVEL_FINE) {

					Printer.print("Analyzer " + Board.moveToString(this.move)
							+ " finished depth=" + depth + " new rating="
							+ result, Printer.LOGLEVEL_FINE);
				}

			} catch (IllegalBoardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// if we lost king we can stop
			if (result <= KING_LOST) {
				// we should give up?
				break;
			}

			// if we won king we can stop
			if (result >= KING_WON) {
				// we should give up?
				break;
			}

			depth++;
		}

		Printer.print("Analyzer finished", Printer.LOGLEVEL_FINEST);
	}

	/**
	 * Simuliert zugfolgen bis zu einem maxlevel. Nach erreichen des Maxlevels
	 * wird die Boardbewertung aufgerufen.
	 * 
	 * maxlevel = 0 - nur ein run
	 * 
	 * maxlevel = 1 - 1 x recusive
	 */
	private int dummyRun(final boolean yours,final int maxlevel,final int startResult)
			throws IllegalBoardException {

		int finalResult = startResult;
		List<byte[]> moveList = null;
		moveList = board.getMoveList(!yours);

		for (int i = 0; i < moveList.size(); i++) {
			// now compute next half move for each move....

			path.add(moveList.get(i));
			Move yourMove = board
					.doMove(moveList.get(i)[0], moveList.get(i)[1]);

			if (0 < maxlevel) {
				// recusive call

				int currentresult  = dummyRun(!yours, maxlevel-1, startResult);

				if (currentresult<startResult && currentresult<finalResult) {
					finalResult=currentresult;
				}
				
				Printer.print("Analyzer zwischenresult=" + finalResult,
						Printer.LOGLEVEL_FINEST);
			} else {
				// rate board
				
//				if ("C3E4".equals(Board.moveToString(moveList.get(i)))) {
//					System.out.println("Hallo");
//					Printer.printBoard(board, Printer.LOGLEVEL_INFO);
//					
//				}
				
				
				int currentresult = board.rateBoard();
				countSituations++;

				if (Printer.logLevel >= Printer.LOGLEVEL_FINEST) {

					String sPath = "";
					for (byte[] amove : path) {
						sPath += " " + Board.moveToString(amove);
					}
					Printer.print("Analyzer " + sPath + " ...... Rating="
							+ currentresult, Printer.LOGLEVEL_FINEST);

				}

				
				if (currentresult<startResult && currentresult<finalResult) {
					finalResult=currentresult;
				}
				
			}

			// remove from path
			path.remove(path.size() - 1);

			board.undoMove(yourMove);

		}

		return finalResult;

	}

	private boolean debug(String move, Move amove, int currentresult) {

		try {
			if (Board.stringToMove(move)[0] == amove.from
					&& Board.stringToMove(move)[1] == amove.to) {
				// Printer.print("+++ Analyzer Debug "+move,
				// Printer.LOGLEVEL_INFO);

				Printer.print("+++ Analyzer Debug d8d5: currentrating="
						+ currentresult + " complete result=" + result
						+ " current depth=" + depth, Printer.LOGLEVEL_INFO);

				return true;

			}
		} catch (IllegalBoardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Isolated code for analyze process
	 * 
	 * @throws IllegalBoardException
	 */
	@Deprecated
	public void runOld() {

		Printer.print("Analyzer started", Printer.LOGLEVEL_FINEST);
		// now start computing all possible reactions and give each reaction a
		// rating

		List<byte[]> moveList;
		try {
			moveList = board.getMoveList(false);
		} catch (IllegalBoardException e) {
			Printer.print(
					"Analyzer error analyzing move list: " + e.getMessage(),
					Printer.LOGLEVEL_INFO);
			e.printStackTrace();
			return;
		}
		Printer.print("Analyzer: computing " + moveList.size()
				+ " possible reactions...", Printer.LOGLEVEL_FINEST);

		// now play each move and rate it.....
		for (int i = 0; i < moveList.size(); i++) {
			// now compute next half move for each move....
			Move yourMove = board
					.doMove(moveList.get(i)[0], moveList.get(i)[1]);
			// and now rate the new board....
			int currentresult = board.rateBoard();
			
			// Log result
			if (Printer.logLevel >= Printer.LOGLEVEL_FINEST) {
				String sMove = Board.moveToString(moveList.get(i));
				Printer.print("Analyzer: " + sMove + ": rating ="
						+ currentresult, Printer.LOGLEVEL_FINEST);
			}

			if (currentresult < result)
				result = currentresult;

			// finally we undo the move to analyze the next one...
			board.undoMove(yourMove);

			// if we lost king we can stop
			if (result < KING_LOST) {
				// we should give up?
				result = KING_LOST;
				break;
			}
		}
		depth++;
		Printer.print("Analyzer finished", Printer.LOGLEVEL_FINEST);
	}

	public Board getBoard() {
		return board;
	}

	public byte[] getMove() {
		return move;
	}

	/**
	 * returns the worthiest result of the current move
	 * 
	 * @return
	 */
	public int getResult() {
		return result;
	}

	

	
}
