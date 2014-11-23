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
public class PawnTest {

	Board board;
	private final static Logger logger = Logger.getLogger(PawnTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	@Test
	public void testA2() {

		try {
			board.placeFigure("A2", Board.PAWN_ME);
			Figure pawn = board.createFigure("A2");
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("A3")));
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("a4")));

			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("B3")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	@Test
	public void testA2Blocked() {
		try {
			board.placeFigure("A2", Board.PAWN_ME);
			board.placeFigure("A3", Board.PAWN_ME);
			Figure pawn = board.createFigure("A2");
			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("A3")));
			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("a4")));

			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("B3")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	@Test
	public void testD2() {

		try {
			board.placeFigure("D2", Board.PAWN_ME);
			Figure pawn = board.createFigure("D2");
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("D3")));
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("D4")));

			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("E3")));
			Assert.assertFalse(pawn.getMoves().contains(Board.getFieldIndex("C3")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	@Test
	public void testBeatD2() {

		try {

			board.placeFigure("D2", Board.PAWN_ME);
			board.placeFigure("C3", Board.PAWN_YOURS);
			board.placeFigure("E3", Board.PAWN_YOURS);
			Figure pawn = board.createFigure("D2");
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("D3")));
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("D4")));

			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("E3")));
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("C3")));

		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	@Test
	public void testBaseLine() {
		try {
			board.placeFigure("A7", Board.PAWN_ME);
			Figure pawn = board.createFigure("A7");
			Assert.assertTrue(pawn.getMoves().contains(Board.getFieldIndex("A8")));
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
}
