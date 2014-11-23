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
			Figure rook = board.createFigure("A1");
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A8")));

			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("b1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("c1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("d1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("e1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("f1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("g1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h1")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("A1")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testH8() {

		try {
			board.placeFigure("h8", Board.ROOK_ME);
			Figure rook = board.createFigure("H8");
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("h1")));

			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("b8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("c8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("d8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("e8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("f8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("g8")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("a8")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("h8")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	@Test
	public void testB2() {

		try {
			board.placeFigure("B4", Board.ROOK_ME);
			Figure rook = board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("E4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("f4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("g4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("B4")));
		
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
			Figure rook = board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("E4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("f4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("g4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("B4")));
		
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
			Figure rook = board.createFigure("B4");
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B3")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B2")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B1")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B5")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B6")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B7")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("B8")));


			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("A4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("C4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("D4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("E4")));
			Assert.assertTrue(rook.getMoves().contains(Board.getFieldIndex("f4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("g4")));
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("H4")));

			
			
			Assert.assertFalse(rook.getMoves().contains(Board.getFieldIndex("B4")));
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

}
