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
public class RatingTest {

	Board board;
	private final static Logger logger = Logger.getLogger(RatingTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_BLACK);
		
		logger.info("setup board");
	}

	
	/**
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   ┌───────────────┐
	 8 │♜ ♞ ♝ ♛ ♚ ♝   ♜│
	 7 │♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│
	 6 │          ♞    │
	 5 │               │
	 4 │        ♙      │
	 3 │    ♘          │
	 2 │♙ ♙ ♙ ♙   ♙ ♙ ♙│
	 1 │♖   ♗ ♕ ♔ ♗ ♘ ♖│
	   └───────────────┘
	    A B C D E F G H
	 * </code>
	 */
	@Test
	public void test1() {

		try {
			board.initNewGame();
			board.doMove(Board.getFieldIndex("E2"), Board.getFieldIndex("E4"));
			board.doMove(Board.getFieldIndex("G8"), Board.getFieldIndex("F6"));
			board.doMove(Board.getFieldIndex("B1"), Board.getFieldIndex("C3"));

			Assert.assertEquals(0, board.rateBoard());
			
			board.doMove(Board.getFieldIndex("F6"), Board.getFieldIndex("E4"));

			Assert.assertEquals(1, board.rateBoard());
			
			board.doMove(Board.getFieldIndex("C3"), Board.getFieldIndex("E4"));
			
			Assert.assertEquals(-2, board.rateBoard());
		
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

	
	
	
	
}
