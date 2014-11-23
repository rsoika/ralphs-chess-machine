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
public class BishopTest {

	Board board;
	private final static Logger logger = Logger.getLogger(BishopTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	
	
	@Test
	public void testB4() {

		try {
			board.placeFigure("B4", Board.BISHOP_ME);
			Figure bishop = board.createFigure("B4");
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A5")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C5")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D6")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("E7")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("F8")));

			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A3")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C3")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D2")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("E1")));

			
			
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("B4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	@Test
	public void testB4BlockedFromMe() {

		try {
			board.placeFigure("B4", Board.BISHOP_ME);
			board.placeFigure("e7", Board.PAWN_ME);
			Figure bishop = board.createFigure("B4");
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A5")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C5")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D6")));
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("E7")));
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("F8")));

			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A3")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C3")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D2")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("E1")));

			
			
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("B4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	

	@Test
	public void testB4BlockedFromYours() {

		try {
			board.placeFigure("B4", Board.BISHOP_ME);
			board.placeFigure("e7", Board.PAWN_YOURS);
			Figure bishop =  board.createFigure("B4");
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A5")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C5")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D6")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("E7")));
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("F8")));

			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("A3")));
			
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("C3")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("D2")));
			Assert.assertTrue(bishop.getMoves().contains(Board.getFieldIndex("E1")));

			
			
			Assert.assertFalse(bishop.getMoves().contains(Board.getFieldIndex("B4")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
}
