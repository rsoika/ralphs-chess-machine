package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;
import com.soika.chess.figures.Figure;

public class BoardTest {

	Board board;
	private final static Logger logger = Logger.getLogger(BoardTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_WHITE);
		logger.info("setup board");
	}

	/**
	 * Test coordiantes of Board
	 */
	@Test
	public void testBoardCoordinates() {

		try {
			Assert.assertTrue((0 == Board.getFieldIndex("A1")));
			Assert.assertTrue((8 == Board.getFieldIndex("A2")));
			Assert.assertTrue((1 == Board.getFieldIndex("B1")));
			Assert.assertTrue((9 == Board.getFieldIndex("B2")));
			Assert.assertTrue((63 == Board.getFieldIndex("h8")));
		} catch (IllegalBoardException e) {

			e.printStackTrace();
			fail();
		}
	}

	/**
	 * test invalid call from createFigure
	 */
	@Test
	public void testCreateFigure() {

		try {
			// exception expected!
			@SuppressWarnings("unused")
			Figure rook = board.createFigure("A1");

			fail();

		} catch (IllegalBoardException e) {

			// works
		}
	}

	@Test
	public void testPlaceFigure() {

		try {
			board.placeFigure("i1", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
			logger.info(e.getMessage());

		}

		try {
			board.placeFigure("b0", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
			logger.info(e.getMessage());

		}

		try {
			board.placeFigure("c9", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
			logger.info(e.getMessage());

		}

		// finally test a legal move
		try {
			board.placeFigure("a1", Board.BISHOP_ME);
			board.placeFigure("A1", Board.BISHOP_ME);
			board.placeFigure("H8", Board.BISHOP_ME);
			board.placeFigure("A8", Board.BISHOP_ME);
		} catch (IllegalBoardException e) {
			e.printStackTrace();
			fail("Illegal move");
			logger.info(e.getMessage());

		}
	}

	@Test
	public void testPlaceFigureSimple() {

		// test legal moves
		try {
			board.placeFigure("A1", Board.BISHOP_ME);
			board.placeFigure("h8", Board.BISHOP_YOURS);
		} catch (IllegalBoardException e) {
			e.printStackTrace();
			fail("Illegal move");
		}

		// test illegal moves
		try {
			board.placeFigure("j1", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
		}
		// try {
		// board.placeFigure(1, 0, Board.BISHOP_ME);
		// fail("Illegal move");
		// } catch (IllegalBoardException e) {
		// // expected
		// }
		// try {
		// board.placeFigure(65, 1, Board.BISHOP_ME);
		// fail("Illegal move");
		// } catch (IllegalBoardException e) {
		// // expected
		// }
		// try {
		// board.placeFigure(1, 65, Board.BISHOP_ME);
		// fail("Illegal move");
		// } catch (IllegalBoardException e) {
		// // expected
		// }

	}

	/**
	 * Tests the convertion from a fild index into the line/row components
	 */
	@Test
	public void testFieldIndexConverter() {

		Assert.assertTrue(1 == Board.lineFromIndex((byte) 0));
		Assert.assertTrue(8 == Board.lineFromIndex((byte) 7));
		Assert.assertTrue(7 == Board.lineFromIndex((byte) 14));
		Assert.assertTrue(1 == Board.lineFromIndex((byte) 16));

		Assert.assertTrue(1 == Board.rowFromIndex((byte) 0));
		Assert.assertTrue(1 == Board.rowFromIndex((byte) 7));
		Assert.assertTrue(2 == Board.rowFromIndex((byte) 8));
		Assert.assertTrue(8 == Board.rowFromIndex((byte) 63));
		
		
		
		//49=B7
		Assert.assertTrue(2 == Board.lineFromIndex((byte) 49));
		Assert.assertTrue(7 == Board.rowFromIndex((byte) 49));
		

	}
}
