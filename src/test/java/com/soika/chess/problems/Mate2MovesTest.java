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
public class Mate2MovesTest {

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
	 8 │               │
	 7 │    ♚          │
	 6 │    ♛     ♟    │
	 5 │            ♝  │
	 4 │    ♖ ♔ ♙   ♞  │
	 3 │    ♙ ♘        │
	 2 │               │
	 1 │               │
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * http://www.schach-tipps.de/schachtraining/taktik/schachaufgaben
	 */
	@Test
	public void testProblem1() {

		try {
			board.placeFigure("C3", Board.PAWN_YOURS);
			board.placeFigure("E4", Board.PAWN_YOURS);
			board.placeFigure("D3", Board.KNIGHT_YOURS);
			board.placeFigure("C4", Board.ROOK_YOURS);
			board.placeFigure("D4", Board.KING_YOURS);

			board.placeFigure("F6", Board.PAWN_ME);
			board.placeFigure("G5", Board.BISHOP_ME);
			board.placeFigure("G4", Board.KNIGHT_ME);
			board.placeFigure("C6", Board.QUEEN_ME);
			board.placeFigure("C7", Board.KING_ME);

			Printer.printBoard(board, Printer.LOGLEVEL_INFO);
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
			Assert.assertEquals("C6C4", sMyMove);

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
			Assert.assertEquals("G5E3", sMyMove);

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
	 6 │    ♛     ♟    │
	 5 │            ♝  │
	 4 │    ♖ ♔ ♙   ♞  │
	 3 │    ♙ ♘        │
	 2 │               │
	 1 │               │
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * http://www.schach-tipps.de/schachtraining/taktik/schachaufgaben
	 */
	@Test
	public void testProblem1SingleAnalyzer() {

		try {
			board.placeFigure("C3", Board.PAWN_YOURS);
			board.placeFigure("E4", Board.PAWN_YOURS);
			board.placeFigure("D3", Board.KNIGHT_YOURS);
			board.placeFigure("C4", Board.ROOK_YOURS);
			board.placeFigure("D4", Board.KING_YOURS);

			board.placeFigure("F6", Board.PAWN_ME);
			board.placeFigure("G5", Board.BISHOP_ME);
			board.placeFigure("G4", Board.KNIGHT_ME);
			board.placeFigure("C6", Board.QUEEN_ME);
			board.placeFigure("C7", Board.KING_ME);

			Printer.printBoard(board, Printer.LOGLEVEL_INFO);

			Printer.print("Debugging C6C4...");
			Analyzer a;

			a = new Analyzer(board, Board.stringToMove("C6C4"), 1);

			a.start();
			a.join();

			Assert.assertTrue(a.getResult() > 0);

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
			fail();
		}

	}
}
