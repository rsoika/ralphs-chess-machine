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
public class KnightTest {

	Board board;
	private final static Logger logger = Logger.getLogger(KnightTest.class
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
	 6│  x   x        │
	 5│x       x      │
	 4│    ♞          │
	 3│x       x      │
	 2│  x   x        │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>	
	 */
	@Test
	public void testB4() {

		try {
			board.placeFigure("C4", Board.KNIGHT_ME);
			Figure knight = board.createFigure("C4");
			Assert.assertTrue(knight.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("D6")));
			
			Assert.assertTrue(knight.getMoves().contains(Board.getField("A5")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("E5")));

			Assert.assertTrue(knight.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("E3")));
			
			Assert.assertTrue(knight.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("D2")));

			Assert.assertFalse(knight.getMoves().contains(Board.getField("B4")));

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
	 6│  x   ♛        │
	 5│x       x      │
	 4│    ♞          │
	 3│x       x      │
	 2│  ♖   x        │
	 1│               │
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>	 */
	@Test
	public void testB4Blocked() {

		try {
			board.placeFigure("D6", Board.QUEEN_ME);
			board.placeFigure("B2", Board.ROOK_YOURS);
			board.placeFigure("C4", Board.KNIGHT_ME);
			Figure knight = board.createFigure("C4");
			Assert.assertTrue(knight.getMoves().contains(Board.getField("B6")));
			Assert.assertFalse(knight.getMoves().contains(Board.getField("D6")));
			
			Assert.assertTrue(knight.getMoves().contains(Board.getField("A5")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("E5")));

			Assert.assertTrue(knight.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("E3")));
			
			Assert.assertTrue(knight.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(knight.getMoves().contains(Board.getField("D2")));

			Assert.assertFalse(knight.getMoves().contains(Board.getField("B4")));
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	

}
