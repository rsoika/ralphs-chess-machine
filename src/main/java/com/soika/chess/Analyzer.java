package com.soika.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * The Analyzer holds a clone of a Boards setup. This internal setup can be
 * modified by the Analyzer to test different moves. There for it is possible to
 * run multiple analyzers parallel for one bord.
 * 
 * The Analyzer computes a full-move. This means: given a possible computer-move
 * the Analyzer computes any possible reaction and rates the new situation. Each
 * Move becomes a rating which can be used for further analysis.
 * 
 * @author rsoika
 *
 */
public class Analyzer {

	// byte[] setup;
	Board board;
	List<Move[]> paths;

	private final static Logger logger = Logger.getLogger(Analyzer.class
			.getName());

	public Analyzer(final Board aboard, byte from, byte to) throws IllegalBoardException {
		super();

		paths = new ArrayList<Move[]>();
		this.board = new Board(aboard.direction);
		// clone board...
		this.board.setup = aboard.setup.clone();
		
		doMove(from, to);
		Printer.printBoard(this.board);
		// now start computing all possible reactions and give each reaction a rating
		List<byte[]> moveList = board.getYoursMoveList();
		// now play each move and rate it.....
		for (int i = 0; i < moveList.size(); i++) {
			// now compute next half move for each move....
			Move yourMove = doMove(moveList.get(i)[0], moveList.get(i)[1]);
			// and now rate the new board....
			int result=rateBoard();
			// Log result
			String sMove= Board.moveToString(moveList.get(i));
			System.out.println("Analyzer: "+sMove+ ": rating ="+result);
			
			// finally we undo the move to analyze the next one...
			undoMove(yourMove);
		}

	}

	public Board getBoard() {
		return board;
	}


	/**
	 * Plays a move on the board and returns the Move Object containing the move
	 * and target figure
	 * 
	 * @param move
	 */
	public Move doMove(byte from, byte to) {
		Move move = new Move(board, from, to);
		board.move(from, to);
		return move;
	}

	/**
	 * plays a path of moves on the board
	 * 
	 * @param path
	 *            - array of moves to be done
	 */
	public void doMovePath(Move[] path) {

		for (Move amove : path) {
			if (amove != null)
				doMove(amove.from, amove.to);
			else
				break;
		}

	}

	/**
	 * reverts a path of moves on the board. (restores also a hit figure)
	 */
	public void undoMovePath(Move[] path) {
		// revert board;
		for (int i = path.length; --i >= 0;) {
			if (path[i] != null)
				undoMove(path[i]);
			else
				continue;
		}
	}

	/**
	 * reverts a move on the board. (restores also a hit figure)
	 */
	public void undoMove(Move move) {
		board.move(move.to, move.from);
		board.setup[move.to] = move.targetFigure;
	}

	
	
	
	/**
	 * This method rates the current board 
	 * 
	 * not very tricky so far..
	 * @return
	 */
	public int rateBoard() {
		int result=0;
		for (byte i=0;i<64;i++) {
			result+=board.getFigure(i);
			
		}
		
		return result;
	}
	
}
