package com.soika.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.soika.chess.exceptions.IllegalBoardException;

/**
 * This class analysis a move for a given board and computes the worst case as a
 * result of that move.
 * 
 * The Analyzer creates a clone of a Boards setup. This internal setup can be
 * modified by the Analyzer to test different moves. There for it is possible to
 * run multiple analyzers parallel for one board.
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
	int result = 0;
	byte[] move;
	public final static byte KING_LOST=-88;

	/**
	 * If one reaction move takes the king we can cancel the analyze process
	 * 
	 * @param aboard
	 * @param amove
	 * @throws IllegalBoardException
	 */
	public Analyzer(final Board aboard, byte[] amove)
			throws IllegalBoardException {
		super();

		this.move = amove;
		result = 0;
		paths = new ArrayList<Move[]>();
		this.board = new Board(aboard.direction);
		// clone board...
		this.board.setup = aboard.setup.clone();

		doMove(amove[0], amove[1]);
		Printer.printBoard(this.board, Printer.LOGLEVEL_FINEST);
		// now start computing all possible reactions and give each reaction a
		// rating
		List<byte[]> moveList = board.getYoursMoveList();
		Printer.print("Analyzer: computing possible reactions...",
				Printer.LOGLEVEL_FINEST);

		// now play each move and rate it.....
		for (int i = 0; i < moveList.size(); i++) {
			// now compute next half move for each move....
			Move yourMove = doMove(moveList.get(i)[0], moveList.get(i)[1]);
			// and now rate the new board....
			int currentresult = rateBoard();
			// Log result
			String sMove = Board.moveToString(moveList.get(i));
			Printer.print("Analyzer: " + sMove + ": rating =" + currentresult,
					Printer.LOGLEVEL_FINEST);

			if (currentresult < result)
				result = currentresult;

			// finally we undo the move to analyze the next one...
			undoMove(yourMove);

			// if we lost king we can stop
			if (result < KING_LOST) {
				// we should give up?
				result=KING_LOST;
				break;
			}

		}

		Printer.print("Analyzer finished", Printer.LOGLEVEL_FINEST);
	}

	public Board getBoard() {
		return board;
	}

	public byte[] getMove() {
		return move;
	}

	/**
	 * returns the worthiest result of the current move
	 * 
	 * @return
	 */
	public int getResult() {
		return result;
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
	 * 
	 * @return
	 */
	public int rateBoard() {
		int result = 0;
		for (byte i = 0; i < 64; i++) {
			result += rateFigure(board.getFigure(i));

		}

		return result;
	}

	/**
	 * This method rates a single figure
	 * 
	 * not very tricky so far....
	 * 
	 * @return
	 */
	public byte rateFigure(byte figure) {

		byte figureRating = 0;

		switch (figure) {
		case Board.ROOK_ME:
			figureRating = 5;
			break;
		case Board.KNIGHT_ME:
			figureRating = 3;
			break;
		case Board.BISHOP_ME:
			figureRating = 3;
			break;
		case Board.QUEEN_ME:
			figureRating = 9;
			break;
		case Board.KING_ME:
			figureRating = 127;
			break;
		case Board.PAWN_ME:
			figureRating = 1;
			break;

		case Board.ROOK_YOURS:
			figureRating = -5;
			break;
		case Board.KNIGHT_YOURS:
			figureRating = -3;
			break;
		case Board.BISHOP_YOURS:
			figureRating = -3;
			break;
		case Board.QUEEN_YOURS:
			figureRating = -9;
			break;
		case Board.KING_YOURS:
			figureRating = -127;
			break;
		case Board.PAWN_YOURS:
			figureRating = -1;
			break;

		default:
			// no op
		}

		return figureRating;

	}
}
