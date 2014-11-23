package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.figures.Figure;

/**
 * test move list for rook
 * 
 * @author rsoika
 *
 */
public class KingTest {

	Board board;
	private final static Logger logger = Logger.getLogger(KingTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	
	/**
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│               │
	 7│               │
	 6│               │
	 5│x x x          │
	 4│x ♛ x          │
	 3│x x x          │
	 2│               │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   B4
     * </code>	
	 */
	@Test
	public void testB4() {

		try {
			board.placeFigure("B4", Board.KING_ME);
			Figure king = board.createFigure("B4");
			Assert.assertTrue(king.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("A5")));
			
			Assert.assertTrue(king.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("B5")));

			Assert.assertTrue(king.getMoves().contains(Board.getField("c3")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("c4")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("c5")));
		
			Assert.assertFalse(king.getMoves().contains(Board.getField("d4")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("b6")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("b2")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("d2")));

			Assert.assertFalse(king.getMoves().contains(Board.getField("B4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	/**
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│               │
	 7│               │
	 6│               │
	 5│x x ♖          │
	 4│x ♛ ♝          │
	 3│x x x          │
	 2│               │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   B4
     * </code>	 */
	@Test
	public void testB4Blocked() {

		try {
			board.placeFigure("B4", Board.KING_ME);
			board.placeFigure("C4", Board.PAWN_ME);
			board.placeFigure("C5", Board.ROOK_YOURS);
			Figure king = board.createFigure("B4");
			Assert.assertTrue(king.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("A5")));
			
			Assert.assertTrue(king.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("B5")));

			Assert.assertTrue(king.getMoves().contains(Board.getField("c3")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("c4")));
			Assert.assertTrue(king.getMoves().contains(Board.getField("c5")));
		
			Assert.assertFalse(king.getMoves().contains(Board.getField("d4")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("b6")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("b2")));
			Assert.assertFalse(king.getMoves().contains(Board.getField("d2")));

			Assert.assertFalse(king.getMoves().contains(Board.getField("B4")));
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	

}
