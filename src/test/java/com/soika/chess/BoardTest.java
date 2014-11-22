package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	Board board;
	private final static Logger logger = Logger
			.getLogger(BoardTest.class.getName());


	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void testPlaceFigure() {

		try {
			board.placeFigure("i1", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
			logger.info(e.getMessage());
		}

		try {
			board.placeFigure("b0", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
			logger.info(e.getMessage());
		}

		try {
			board.placeFigure("c9", Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
			logger.info(e.getMessage());
		}

		// finally test a legal move
		try {
			board.placeFigure("a1", Board.BISHOP_ME);
			board.placeFigure("A1", Board.BISHOP_ME);
			board.placeFigure("H8", Board.BISHOP_ME);
			board.placeFigure("A8", Board.BISHOP_ME);
		} catch (IllegalMoveException e) {
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
		} catch (IllegalMoveException e) {
			e.printStackTrace();
			fail("Illegal move");
		}

		// test illegal moves
		try {
			board.placeFigure(0, 1, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
		}
		try {
			board.placeFigure(1, 0, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
		}
		try {
			board.placeFigure(65, 1, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
		}
		try {
			board.placeFigure(1, 65, Board.BISHOP_ME);
			fail("Illegal move");
		} catch (IllegalMoveException e) {
			// expected
		}

	}
}
