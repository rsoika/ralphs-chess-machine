package com.soika.chess.problems;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.Analyzer;
import com.soika.chess.Board;
import com.soika.chess.ChessMashine;
import com.soika.chess.Printer;
import com.soika.chess.exceptions.CheckMateException;
import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.exceptions.RemisException;
import com.soika.chess.figures.Figure;

/**
 * test chessMashine
 * 
 * Mate in 2 moves
 * 
 * @author rsoika
 *
 */
public class SimpleMovesTest {

	Board board;

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_BLACK);
		Printer.logLevel = Printer.LOGLEVEL_FINE;

		Printer.printBoard(board, Printer.LOGLEVEL_INFO);

	}

	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♜   ♝ ♛ ♚ ♝ ♞ ♜│
	 7 │♟ ♟ ♟ ♟ ♟   ♟ ♟│
	 6 │    ♞     ♟    │
	 5 │               │
	 4 │        ♙      │
	 3 │    ♘     ♘    │
	 2 │♙ ♙ ♙ ♙   ♙ ♙ ♙│
	 1 │♖   ♗ ♕ ♔ ♗   ♖│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 */
	public void setupProblem1() {

		board.initNewGame();
		try {
			board.doMove(Board.getFieldIndex("E2"), Board.getFieldIndex("E4"));
			board.doMove(Board.getFieldIndex("F7"), Board.getFieldIndex("F6"));
			board.doMove(Board.getFieldIndex("G1"), Board.getFieldIndex("F3"));
			board.doMove(Board.getFieldIndex("B8"), Board.getFieldIndex("C6"));
			board.doMove(Board.getFieldIndex("B1"), Board.getFieldIndex("C3"));

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♜ ♞ ♝ ♛ ♚ ♝   ♜│
	 7 │♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│
	 6 │          ♞    │
	 5 │               │
	 4 │        ♙      │
	 3 │    ♘          │
	 2 │♙ ♙ ♙ ♙   ♙ ♙ ♙│
	 1 │♖   ♗ ♕ ♔ ♗ ♘ ♖│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 */
	public void setupProblem2() {

		board.initNewGame();
		try {
			board.doMove(Board.getFieldIndex("E2"), Board.getFieldIndex("E4"));
			board.doMove(Board.getFieldIndex("G8"), Board.getFieldIndex("F6"));
			board.doMove(Board.getFieldIndex("B1"), Board.getFieldIndex("C3"));

		} catch (IllegalBoardException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testProblem1() {

		setupProblem1();
		try {

			// First analyze one move => expected C6C4
			ChessMashine chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(2);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			Analyzer myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() == 0);
			String sMyMove = Board.moveToString(myAnalyzedMove.getMove());

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		} catch (RemisException e) {
			fail();
			e.printStackTrace();
		} catch (CheckMateException e) {
			fail();
			e.printStackTrace();
		}

	}

	@Test
	public void testProblem1SingleAnalyzerC6B8() {
		setupProblem1();
		try {

			Printer.print("Debugging C6B8...");
			Analyzer a;
			Printer.logLevel = Printer.LOGLEVEL_FINEST;
			a = new Analyzer(board, Board.stringToMove("C6B8"), 2);

			a.start();
			a.join();

			Assert.assertEquals(0, a.getResult());

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
			fail();
		}

	}
	
	
	

	@Test
	public void testProblem2() {
		setupProblem2();
		try {
	
			// First analyze one move => expected C6C4
			ChessMashine chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(2);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
	
			Analyzer myAnalyzedMove = chessMashine.stop();
	
			Assert.assertTrue(myAnalyzedMove.getResult() == 0);
			String sMyMove = Board.moveToString(myAnalyzedMove.getMove());
	
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		} catch (RemisException e) {
			fail();
			e.printStackTrace();
		} catch (CheckMateException e) {
			fail();
			e.printStackTrace();
		}
	
	}

	@Test
	public void testProblem2SingleAnalyzerF6E4() {
		
		try {
			setupProblem2();
			Printer.print("Debugging F6E4...");
			Analyzer a;
			Printer.logLevel = Printer.LOGLEVEL_FINEST;
			a = new Analyzer(board, Board.stringToMove("F6E4"), 1);

			a.start();
			a.join();

			Assert.assertEquals(-2, a.getResult());
			
			
			// now level 2
			setupProblem2();
			a = new Analyzer(board, Board.stringToMove("F6E4"), 2);

			a.start();
			a.join();

			Assert.assertEquals(-2, a.getResult());
			

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
			fail();
		}

	}
}
