package com.soika.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.soika.chess.exceptions.CheckMateException;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.RemisException;

public class ChessMashine {

	public Analyzer computeBestMove(Board board) throws RemisException, IllegalBoardException, CheckMateException {
		// now computer moves...

		long l = System.currentTimeMillis();
		List<byte[]> myMoves = board.getMyMoveList();
		Printer.print(
				"....." + myMoves.size() + " moves found in : "
						+ (System.currentTimeMillis() - l) + "ms",
				Printer.LOGLEVEL_INFO);
		if (myMoves.size() == 0) {
			throw new RemisException();
		}

		l = System.currentTimeMillis();
		List<Analyzer> bestMoves = analyzeMoves(myMoves,board);

		// moves found?
		if (!(bestMoves.size() > 0)) {
			throw new CheckMateException();
		}

		Printer.print("....." + bestMoves.size()
				+ " usefull moves analysed in : "
				+ (System.currentTimeMillis() - l) + "ms",
				Printer.LOGLEVEL_INFO);

		// randomize a move....
		int moveNumber = randInt(0, bestMoves.size() - 1);

		Analyzer myAnalyzedMove = bestMoves.get(moveNumber);
		
		return myAnalyzedMove;
	}

	

	

	/**
	 * This method analyzes a list of moves and returns the list with the best
	 * moves (worst moves are removed)
	 * 
	 * @param moves
	 * @throws IllegalBoardException
	 */
	public List<Analyzer> analyzeMoves(List<byte[]> moves,Board board) {
		Printer.print("Start analyizing.....", Printer.LOGLEVEL_INFO);
		List<Analyzer> analyzerList = new ArrayList<Analyzer>();
		List<Analyzer> bestMoves = new ArrayList<Analyzer>();

		Printer.print("ChessMashine: Moves analyed: ", Printer.LOGLEVEL_FINE);
		for (byte[] move : moves) {
			Analyzer a;
			try {
				a = new Analyzer(board, move);
				if (a.result > Analyzer.KING_LOST) {
					analyzerList.add(a);
				}
			} catch (IllegalBoardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// sort moves...
		Collections.sort(analyzerList, new AnalyzerComparator());

		// print sorted move list
		if (Printer.logLevel >= Printer.LOGLEVEL_FINE) {
			for (Analyzer a : analyzerList) {
				Printer.print("      : " + Board.moveToString(a.move) + "="
						+ a.result, Printer.LOGLEVEL_FINE);
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
						+ a.result, Printer.LOGLEVEL_FINE);
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

	
	public class AnalyzerComparator implements Comparator<Analyzer> {

		public int compare(Analyzer a1, Analyzer a2) {

			return a2.getResult() - a1.getResult();
		}
	}

}
