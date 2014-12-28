package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * test move list for rook
 * 
 * @author rsoika
 *
 */
public class AnalyzerTest {

	Board board;
	private final static Logger logger = Logger.getLogger(AnalyzerTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_BLACK);
		logger.info("setup board");
		board.initNewGame();
	}

	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜│
	 7 │♟ ♟   ♟ ♟ ♟ ♟ ♟│
	 6 │               │
	 5 │    ♟          │
	 4 │      ♙        │
	 3 │               │
	 2 │♙ ♙ ♙   ♙ ♙ ♙ ♙│
	 1 │♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * This test tests if after D2D4, C7C5 is a good or bad move
	 */
	@Test
	public void testSimple_D2D4_C7C5() {

		try {

			Assert.assertTrue((Board.PAWN_YOURS == board.getFigure("A2")));
			Assert.assertTrue((Board.KING_ME == board.getFigure("E8")));

			board.move("D2", "D4");
			
			Assert.assertTrue((Board.PAWN_YOURS == board.getFigure("D4")));
			Assert.assertTrue((Board.PAWN_ME == board.getFigure("C7")));
			
			
			logger.info("start bad move...");
			// analyze bad move...
			Analyzer analyzerBad = new Analyzer(board, Board.stringToMove(
					"C7C5"));
		
			
			Assert.assertTrue((Board.PAWN_YOURS == board.getFigure("D4")));
			Assert.assertTrue((Board.PAWN_ME == board.getFigure("C7")));
		
			logger.info("start good move...");
			// analyse good ove..
			Analyzer analyzerGood = new Analyzer(board, Board.stringToMove("D7D5"));
			
			
			
			Assert.assertTrue(analyzerBad.getResult()<analyzerGood.getResult());
			
			//analyzer.computeNextMove();

			// test if figures are still in place...

//			Assert.assertTrue((Board.PAWN_YOURS == analyzerBad.getBoard().getFigure("A2")));
//			Assert.assertTrue((Board.KING_ME == analyzerBad.getBoard().getFigure("E8")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	
	
	/**
	 *
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │  ♚            │
	 7 │               │
	 6 │               │
	 5 │               │
	 4 │               │
	 3 │               │
	 2 │               │
	 1 │♖              │
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 * 
	 * This test tests if after D2D4, C7C5 is a good or bad move
	 */
	@Test
	public void testSimple_KingTest() {

		try {

			board = new Board(Board.DIRECTION_BLACK);
			
			board.placeFigure("A1", Board.ROOK_YOURS);
			board.placeFigure("B8", Board.KING_ME);
			
			int figure=board.getFigure("B8");
			System.out.println("figure=" + figure);
			
			logger.info("start bad move...");
			// analyze bad move...
			Analyzer analyzerBad = new Analyzer(board, Board.stringToMove(
					"B8A8"));
		
			logger.info("start good move...");
			// analyse good ove..
			Analyzer analyzerGood = new Analyzer(board, Board.stringToMove("B8C8"));
			Assert.assertTrue(analyzerBad.getResult()<analyzerGood.getResult());
			

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	
	/**
	 * tests the doMovePath and undoMovePath feature
	 */
	@Test
	public void testDoUnDoAMovePath() {

		try {
			// build a path
			// add path
			Move[] movelist = new Move[3];

			Analyzer analyzer = new Analyzer(board, Board.stringToMove("e2e4"));

			Move amove1 = analyzer.doMove(Board.getFieldIndex("d7"),
					Board.getFieldIndex("d5"));
			movelist[0] = amove1;
			// test pawn on d5
			Assert.assertTrue((Board.PAWN_ME == analyzer.getBoard().getFigure(
					"D5")));

			Move amove2 = analyzer.doMove(Board.getFieldIndex("e4"),
					Board.getFieldIndex("d5"));
			movelist[1] = amove2;
			// test pawn on d5
			Assert.assertTrue((Board.PAWN_YOURS == analyzer.getBoard()
					.getFigure("D5")));

			Move amove3 = analyzer.doMove(Board.getFieldIndex("d8"),
					Board.getFieldIndex("d5"));
			movelist[2] = amove3;

			// test queen on d5
			Assert.assertTrue((Board.QUEEN_ME == analyzer.getBoard().getFigure(
					"D5")));

			// roll back all moves
			analyzer.undoMove(amove3);
			analyzer.undoMove(amove2);
			analyzer.undoMove(amove1);
			Assert.assertTrue((Board.QUEEN_ME == analyzer.getBoard().getFigure(
					"D8")));
			Assert.assertTrue((Board.PAWN_YOURS == analyzer.getBoard().getFigure(
					"E4")));
			
			
			// now move the full path...
//			analyzer.doMovePath(movelist);
//			// test queen on d5
//			Assert.assertTrue((Board.QUEEN_ME == analyzer.getBoard().getFigure(
//					"D5")));
//			
//			// now undo full pth
//			analyzer.undoMovePath(movelist);
//			Assert.assertTrue((Board.QUEEN_ME == analyzer.getBoard().getFigure(
//					"D8")));
//			Assert.assertTrue((Board.PAWN_YOURS == analyzer.getBoard().getFigure(
//					"E4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	
}
