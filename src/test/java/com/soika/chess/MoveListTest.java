package com.soika.chess;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * Tests the computed move list of a borard
 * 
 * @author rsoika
 *
 */
public class MoveListTest {

	Board board;
	private final static Logger logger = Logger.getLogger(MoveListTest.class
			.getName());

	@Before
	public void setup() {
		board = new Board(Board.DIRECTION_WHITE);
		logger.info("setup board");
	}

	/**
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│        ♚      │
	 7│               │
	 6│  x   x        │
	 5│x       x      │
	 4│    ♞          │
	 3│x       x      │
	 2│  x   x       ♟│
	 1│        ♔      │
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>
	 */
	@Test
	public void testB4() {

		try {
			board.placeFigure("C4", Board.KNIGHT_ME);
			board.placeFigure("H2", Board.PAWN_ME);
			board.placeFigure("E8", Board.KING_ME);
			board.placeFigure("E1", Board.KING_YOURS);
			

			List<byte[]> moveList = board.getMoveList(true);

			Assert.assertTrue(moveList.size() == 15);
			Assert.assertTrue(Board.isValidMove(moveList, 26, 9));

			Assert.assertFalse(Board.isValidMove(moveList, 26, 10));

			// test pawn
			Assert.assertTrue(Board.isValidMove(moveList, 15, 23));
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
	 8│♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜│
	 7│♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│   Computer
	 6│               │
	 5│               │
	 4│               │
	 3│               │
	 2│♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙│    Human
	 1│♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖│
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>
	 */
	@Test
	public void testNewGame() {

		try {
			board = new Board(Board.DIRECTION_BLACK);
			board.initNewGame();

			List<byte[]> myMoveList = board.getMoveList(true);

			Assert.assertTrue(myMoveList.size() == 20);

			// test my pawn
			Assert.assertTrue(Board.isValidMove(myMoveList, 48, 40));

			// test my knight
			Assert.assertTrue(Board.isValidMove(myMoveList,
					Board.getFieldIndex("B8"), Board.getFieldIndex("A6")));
			Assert.assertTrue(Board.isValidMove(myMoveList,
					Board.getFieldIndex("B8"), Board.getFieldIndex("C6")));

			// test your list...
			List<byte[]> yoursMoveList = board.getMoveList(false);
			Assert.assertTrue(yoursMoveList.size() == 20);
			// test your pawn
			Assert.assertTrue(Board.isValidMove(yoursMoveList,
					Board.getFieldIndex("B2"), Board.getFieldIndex("B3")));

			// test your knight
			Assert.assertTrue(Board.isValidMove(yoursMoveList,
					Board.getFieldIndex("B1"), Board.getFieldIndex("A3")));
			Assert.assertTrue(Board.isValidMove(yoursMoveList,
					Board.getFieldIndex("B1"), Board.getFieldIndex("C3")));

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
	 8│♚              │
	 7│              ♖│
	 6│               │
	 5│               │
	 4│               │
	 3│               │
	 2│               │
	 1│♖              │
	  └───────────────┘
	   A B C D E F G H

	   C4
     * </code>
	 */
	@Test
	public void testCheck() {

		try {
			board.placeFigure("A8", Board.KING_ME);
			board.placeFigure("A1", Board.ROOK_YOURS);
			board.placeFigure("H7", Board.ROOK_YOURS);

			// get my movelist
			List<byte[]> moveList = board.getMoveList(true);

			Assert.assertEquals(1,moveList.size());
			
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}

}
