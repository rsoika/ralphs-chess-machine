package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.figures.Queen;

/**
 * test move list for rook
 * 
 * @author rsoika
 *
 */
public class QueenTest {

	Board board;
	private final static Logger logger = Logger.getLogger(QueenTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	
	
	@Test
	public void testA1() {

		try {
			board.placeFigure("A1", Board.QUEEN_ME);
			Queen queen = (Queen) board.createFigure("A1");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A8")));

			Assert.assertTrue(queen.getMoves().contains(Board.getField("b1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("c1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("d1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("e1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("f1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("g1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h1")));

			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("A1")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testH8() {

		try {
			board.placeFigure("h8", Board.QUEEN_ME);
			Queen queen = (Queen) board.createFigure("H8");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h1")));

			Assert.assertTrue(queen.getMoves().contains(Board.getField("b8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("c8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("d8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("e8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("f8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("g8")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("a8")));

			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("h8")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	@Test
	public void testB2() {

		try {
			board.placeFigure("B4", Board.QUEEN_ME);
			Queen queen = (Queen) board.createFigure("B4");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(queen.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("f4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("g4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	

	@Test
	public void testB2BlockedByMe() {

		try {
			board.placeFigure("B4", Board.QUEEN_ME);
			board.placeFigure("F4", Board.PAWN_ME);
			Queen queen = (Queen) board.createFigure("B4");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(queen.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("f4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("g4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	

	@Test
	public void testB2BlockedByYours() {

		try {
			board.placeFigure("B4", Board.QUEEN_ME);
			board.placeFigure("F4", Board.PAWN_YOURS);
			Queen queen = (Queen) board.createFigure("B4");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(queen.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("f4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("g4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void testB4() {

		try {
			board.placeFigure("B4", Board.QUEEN_ME);
			Queen queen = (Queen) board.createFigure("B4");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A5")));
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("F8")));

			Assert.assertTrue(queen.getMoves().contains(Board.getField("A3")));
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E1")));

			
			
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b8")));
			
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("a4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("c4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("d4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("e4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("f4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("g4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("h4")));
			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("B4")));

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
	 8│  x            │
	 7│  x     ♖      │
	 6│  x   x        │
	 5│x x x          │
	 4│x ♛ x x x ♝    │
	 3│x x x          │
	 2│  x   x        │
	 1│  x     x      │
	  └───────────────┘
	   A B C D E F G H

	   B4
     * </code>	 */
	@Test
	public void testB4Blocked() {

		try {
			board.placeFigure("B4", Board.QUEEN_ME);
			board.placeFigure("F4", Board.PAWN_ME);
			board.placeFigure("E7", Board.ROOK_YOURS);
			Queen queen = (Queen) board.createFigure("B4");
			Assert.assertTrue(queen.getMoves().contains(Board.getField("A5")));
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E7")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("F8")));

			Assert.assertTrue(queen.getMoves().contains(Board.getField("A3")));
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("C3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("D2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("E1")));

			
			
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b2")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b1")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b5")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b6")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b7")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("b8")));
			
			
			Assert.assertTrue(queen.getMoves().contains(Board.getField("a4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("c4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("d4")));
			Assert.assertTrue(queen.getMoves().contains(Board.getField("e4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("f4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("g4")));
			Assert.assertFalse(queen.getMoves().contains(Board.getField("h4")));
			
			
			Assert.assertFalse(queen.getMoves().contains(Board.getField("B4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	

}
