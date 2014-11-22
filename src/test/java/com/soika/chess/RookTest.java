package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.figures.Rook;

/**
 * test move list for rook
 * 
 * @author rsoika
 *
 */
public class RookTest {

	Board board;
	private final static Logger logger = Logger.getLogger(RookTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	
	
	@Test
	public void testA1() {

		try {
			board.placeFigure("A1", Board.ROOK_ME);
			Rook rook = (Rook) board.createFigure("A1");
			Assert.assertTrue(rook.getMoves().contains(Board.getField("A2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("A3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("A7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("A8")));

			Assert.assertTrue(rook.getMoves().contains(Board.getField("b1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("c1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("d1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("e1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("f1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("g1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h1")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getField("A1")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testH8() {

		try {
			board.placeFigure("h8", Board.ROOK_ME);
			Rook rook = (Rook) board.createFigure("H8");
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("h1")));

			Assert.assertTrue(rook.getMoves().contains(Board.getField("b8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("c8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("d8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("e8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("f8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("g8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("a8")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getField("h8")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	@Test
	public void testB2() {

		try {
			board.placeFigure("B4", Board.ROOK_ME);
			Rook rook = (Rook) board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("E4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("f4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("g4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	

	@Test
	public void testB2BlockedByMe() {

		try {
			board.placeFigure("B4", Board.ROOK_ME);
			board.placeFigure("F4", Board.PAWN_ME);
			Rook rook = (Rook) board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("E4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getField("f4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getField("g4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	

	@Test
	public void testB2BlockedByYours() {

		try {
			board.placeFigure("B4", Board.ROOK_ME);
			board.placeFigure("F4", Board.PAWN_YOURS);
			Rook rook = (Rook) board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getField("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("E4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getField("f4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getField("g4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getField("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getField("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

}
