package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;

public class BoardTest {

	Board board;
	private final static Logger logger = Logger
			.getLogger(BoardTest.class.getName());


	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	/**
	 * Test coordiantes of Board
	 */
	@Test
	public void testBoardCoordinates() {

		try {
			Assert.assertTrue((0==Board.getField("A1")));
			Assert.assertTrue((8==Board.getField("A2")));
			Assert.assertTrue((1==Board.getField("B1")));
			Assert.assertTrue((9==Board.getField("B2")));
			Assert.assertTrue((63==Board.getField("h8")));
		} catch (IllegalBoardException e) {
			
			e.printStackTrace();
			fail();
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
			board.placeFigure(1, 1, Board.BISHOP_ME);
			board.placeFigure(8, 8, Board.BISHOP_YOURS);
		} catch (IllegalBoardException e) {
			e.printStackTrace();
			fail("Illegal move");
		}

		// test illegal moves
		try {
			board.placeFigure(0, 1, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
		}
		try {
			board.placeFigure(1, 0, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
		}
		try {
			board.placeFigure(65, 1, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
		}
		try {
			board.placeFigure(1, 65, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalBoardException e) {
			// expected
		}

	}
}
