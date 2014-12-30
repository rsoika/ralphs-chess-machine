package com.soika.chess.problems;

import static org.junit.Assert.fail;
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

/**
 * test chessMashine
 * 
 * winn the pawn
 * 
 * @author rsoika
 *
 */
public class WinnPawnTest {

	Board board;

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_BLACK);
		Printer.logLevel = Printer.LOGLEVEL_FINE;
	}

	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♚              │
	 7 │               │
	 6 │               │
	 5 │      ♟        │
	 4 │    ♙          │
	 3 │               │
	 2 │               │
	 1 │              ♔│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * http://www.schach-tipps.de/schachtraining/taktik/schachaufgaben
	 */
	@Test
	public void testProblem1() {

		try {
			board.placeFigure("H1", Board.KING_YOURS);
			board.placeFigure("C4", Board.PAWN_YOURS);

			board.placeFigure("A8", Board.KING_ME);
			board.placeFigure("D5", Board.PAWN_ME);

			// First analyze one move => expected C6C4
			ChessMashine chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(1);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			Analyzer myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() > 0);
			String sMyMove = Board.moveToString(myAnalyzedMove.getMove());
			Assert.assertEquals("D5C4", sMyMove);

			
			
			// Now analyze 2 move => expected G5E3
			chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(2);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() > 0);
			sMyMove = Board.moveToString(myAnalyzedMove.getMove());
			Assert.assertEquals("D5C4", sMyMove);

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
	
	
	
	
	
	
	
	
	
	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♚              │
	 7 │               │
	 6 │               │
	 5 │      ♟        │
	 4 │    ♙          │
	 3 │               │
	 2 │            ♗  │
	 1 │              ♔│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * http://www.schach-tipps.de/schachtraining/taktik/schachaufgaben
	 */
	@Test
	public void testProblem2() {

		try {
			board.placeFigure("H1", Board.KING_YOURS);
			board.placeFigure("C4", Board.PAWN_YOURS);
			board.placeFigure("G2", Board.BISHOP_YOURS);

			board.placeFigure("A8", Board.KING_ME);
			board.placeFigure("D5", Board.PAWN_ME);

			// First analyze one move => expected C6C4
			ChessMashine chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(1);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			Analyzer myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() ==-4);
			String sMyMove = Board.moveToString(myAnalyzedMove.getMove());
			Assert.assertTrue( sMyMove.startsWith("A8"));

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
	
	
	
	
	
	
	
	

	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │               │
	 7 │    ♚          │
	 6 │    ♞          │
	 5 │      ♟        │
	 4 │    ♙          │
	 3 │               │
	 2 │            ♗  │
	 1 │              ♔│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * http://www.schach-tipps.de/schachtraining/taktik/schachaufgaben
	 */
	@Test
	public void testProblem3() {

		try {
			board.placeFigure("H1", Board.KING_YOURS);
			board.placeFigure("C4", Board.PAWN_YOURS);
			board.placeFigure("G2", Board.BISHOP_YOURS);

			board.placeFigure("C7", Board.KING_ME);
			board.placeFigure("C6", Board.KNIGHT_ME);
			board.placeFigure("D5", Board.PAWN_ME);

			// First analyze one move => expected C6C4
			ChessMashine chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(1);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			Analyzer myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() ==-1);
			String sMyMove = Board.moveToString(myAnalyzedMove.getMove());
			Assert.assertFalse("D5C4".equals(sMyMove));

			
			// with depth 2 d5c4 should be the result
			chessMashine = new ChessMashine();
			chessMashine.setAnalyzingDepth(2);
			chessMashine.start(board);
			try {
				Printer.print("let me think just a second....",
						Printer.LOGLEVEL_INFO);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			 myAnalyzedMove = chessMashine.stop();

			Assert.assertTrue(myAnalyzedMove.getResult() ==1);
			 sMyMove = Board.moveToString(myAnalyzedMove.getMove());
			Assert.assertEquals("D5C4",sMyMove);

			
			
			
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
}
