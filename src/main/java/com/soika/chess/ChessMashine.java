package com.soika.chess;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.soika.chess.exceptions.CheckMateException;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.RemisException;

/**
 * This class implements the main code to analyze a move. The ChessMashine can
 * be started and stopped after a some seconds of thinking.
 * 
 * After the chessMashine has started, the class instantiates for each possible
 * move a Analyzer class which is running a deep analyzes.
 * 
 * When the method stop is called, the chessMashine stops all Analyzers and
 * returns the best Move found so far.
 * 
 * 
 * @author rsoika
 *
 */
public class ChessMashine {

	List<Analyzer> analyzerList = null;
//	List<Thread> threadList = null;
	long startTime;
	private int analyzingDepth=1;

	
	public void setAnalyzingDepth(int analyzingDepth) {
		this.analyzingDepth = analyzingDepth;
		
		Printer.print("ChessMashine analyzing depth="+analyzingDepth, Printer.LOGLEVEL_INFO);
	}

	
	/**
	 * This method starts for each possible move an analyzer. The Analyzer runs
	 * as a backend tread and tries to find out how good the move is.
	 * 
	 * 
	 * es a list of moves and returns the list with the best moves (worst moves
	 * are removed)
	 * 
	 * @param moves
	 * @throws RemisException
	 * @throws IllegalBoardException
	 */
	public void start(Board board) throws RemisException {

		startTime = System.currentTimeMillis();

//		threadList = new ArrayList<Thread>();
		analyzerList=new ArrayList<Analyzer>();
	
		// get my move list
		List<byte[]> moves = null;
		try {
			moves = board.getMoveList(true);
			if (moves.size() == 0) {
				// if no moves possible - remies!
				throw new RemisException();
			}
		} catch (IllegalBoardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		Printer.print("Start analyizing " + moves.size()  + " possible moves.....", Printer.LOGLEVEL_INFO);
		for (byte[] move : moves) {
			Analyzer a;
			try {
				a = new Analyzer(board, move,analyzingDepth);
				// a.start();

			//	Thread t1 = new Thread(a);
			//	threadList.add(t1);
				
				a.start();

				if (a.result > Analyzer.KING_LOST) {
					analyzerList.add(a);
				}
			} catch (IllegalBoardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
	}

	/**
	 *  This method stops all running analyzes threads and returns the best move found so far.
	 * 
	 * @return
	 * @throws RemisException
	 * @throws IllegalBoardException
	 * @throws CheckMateException
	 */
	public Analyzer stop() throws RemisException, IllegalBoardException,
			CheckMateException {

		long countSituations=0;
	
		boolean bAllFinished=true;
		for (Analyzer t : analyzerList) {
			
			if (t.isAlive()) {
			t.interrupt();
			bAllFinished=false;
			}
			countSituations+=t.countSituations;
		}
		
		if (!bAllFinished) {
			Printer.print("** Oh! ich war noch nicht fertig :-((", Printer.LOGLEVEL_INFO);
			
			
		}

		// filter best moves....
		List<Analyzer> bestMoves = filterBestMoves();

		// moves found?
		if (!(bestMoves.size() > 0)) {
			throw new CheckMateException();
		}

		Printer.print(
				"....." + countSituations + " szeanrios analyzed in "
						+ (System.currentTimeMillis() - startTime) + "ms",
				Printer.LOGLEVEL_INFO);
		Printer.print(
				"....." + bestMoves.size() + " seemingly reasonable moves found.",
				Printer.LOGLEVEL_INFO);

		// randomize a move....
		int moveNumber = randInt(0, bestMoves.size() - 1);

		Analyzer myAnalyzedMove = bestMoves.get(moveNumber);

		return myAnalyzedMove;
	}

	/**
	 * This method test the current running Anaylzers and takes out the best
	 * moves found so far. Remember- the Analyzer is a backend thread running
	 * endless
	 * 
	 * @return
	 */
	private List<Analyzer> filterBestMoves() {
		Printer.print("ChessMashine: filterBestMoves... ", Printer.LOGLEVEL_FINE);
		
		List<Analyzer> bestMoves = new ArrayList<Analyzer>();

		// sort moves...
		Collections.sort(analyzerList, new AnalyzerComparator());

		// print sorted move list
		if (Printer.logLevel >= Printer.LOGLEVEL_FINE) {
			Printer.print("ChessMashine: Analyzer Result=", Printer.LOGLEVEL_FINE);
			for (Analyzer a : analyzerList) {
				Printer.print("        " + Board.moveToString(a.move) + "="
						+ a.result + " (depth=" + a.depth + ")", Printer.LOGLEVEL_FINE);
			}
		}

		// get best moves ...
		Printer.print("ChessMashine: get best moves: ", Printer.LOGLEVEL_FINE);
		Analyzer bestAnalyzer = null;
		for (Analyzer a : analyzerList) {
			if (bestAnalyzer == null
					|| (!(a.getResult() < bestAnalyzer.getResult()))) {
				bestMoves.add(a);
				bestAnalyzer = a;
				Printer.print("      : " + Board.moveToString(a.move) + "="
						+ a.result+ " (depth=" + a.depth + ")", Printer.LOGLEVEL_FINE);
			} else {
				// not more good moves

				Printer.print(" stop sorting best mvoes. Last move checked: "
						+ Board.moveToString(a.move) + "=" + a.result,
						Printer.LOGLEVEL_FINE);

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

	
	/**
	 * Analyzer Comperator - compare rating and depth. 
	 * @author rsoika
	 *
	 */
	public class AnalyzerComparator implements Comparator<Analyzer> {

		public int compare(Analyzer a1, Analyzer a2) {

			// if result equals than we sort by depth
			if (a2.getResult() == a1.getResult())
				return a2.depth - a1.depth;
			
			// sort by result
			return a2.getResult() - a1.getResult();
		}
	}

}
