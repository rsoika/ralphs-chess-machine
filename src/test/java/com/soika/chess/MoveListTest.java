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
 * @author rsoika
 *
 */
public class MoveListTest {

	Board board;
	private final static Logger logger = Logger
			.getLogger(MoveListTest.class.getName());


	@Before
	public void setup() {
		board = new Board();
		logger.info("setup board");
	}

	/**
	 * <code>
	  ♔♕♖♗♘♙♚♛♜♝♞♟
	   A B C D E F G H
	  ┌───────────────┐
	 8│               │
	 7│               │
	 6│  x   x        │
	 5│x       x      │
	 4│    ♞          │
	 3│x       x      │
	 2│  x   x       ♟│
	 1│               │
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
			
			List<byte[]> moveList=board.getMyMoveList();
			
			Assert.assertTrue(moveList.size()==10);
			byte[] move = new byte[2];
			move[0] = 26;
			move[1] = 9;
			Assert.assertTrue(containsMove(moveList,move));

			move[0] = 26;
			move[1] = 10;
			Assert.assertFalse(containsMove(moveList,move));
			
			// test pawn
			move[0] = 15;
			move[1] = 23;
			Assert.assertTrue(containsMove(moveList,move));
		} catch (IllegalBoardException e) {
			fail();
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Helper method to compare a byte[] list
	 * @param moveList
	 * @param move
	 * @return
	 */
	private boolean containsMove (List<byte[]> moveList,byte[] move ) {
		for (byte[] amove : moveList) {
			if (amove[0]==move[0] && amove[1]==move[1])
				return true;
		}
		return false;
	}
	
}
