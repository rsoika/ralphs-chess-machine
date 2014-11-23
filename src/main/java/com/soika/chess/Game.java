package com.soika.chess;

import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.soika.chess.exceptions.IllegalBoardException;

public class Game {
	private static Game instance = null;
	Board board;
	static Scanner userInput;
	private final static Logger logger = Logger.getLogger(Game.class.getName());

	public static void main(String[] args) {

		Game tmp = Game.getInstance();
		tmp.init();
		tmp.play();
	}

	/* Static 'instance' method */
	public static Game getInstance() {
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return instance;
	}

	/**
	 * Setup a new game (comp is black)
	 */
	public void init() {

		print("*************************");
		print("* Ralphs Chess Maschine *");
		print("* V0.0.1                *");
		print("*************************");

		userInput = new Scanner(System.in);

		board = new Board(Board.DIRECTION_BLACK);
		print("Setup the board...");

		board.initNewGame();
		print("Lets play...");

	}

	public void play() {
		while (true) {
			System.out.print("Please enter your move : ");
			String sMove = userInput.nextLine();
			
			// quit?
			if (sMove.toLowerCase().startsWith("q"))
				break;
			
			// validate move
			if (sMove.length()>4) {
				print("Illegal move!");
				continue;
			}

			// evaluate move
			byte moveFrom;
			byte moveTo;
			try {
				 moveFrom=board.getFieldIndex(sMove.substring(0,2));
				 moveTo=board.getFieldIndex(sMove.substring(2));
				 if (!Board.isValidMove(board.getYoursMoveList(), moveFrom, moveTo)) {
					 print("Illegal move!");
						continue;
				 }
				 
			} catch (IllegalBoardException e) {
				print("Illegal move! ("+e.getMessage()+")");
				//logger.severe(e.toString());
				continue;
			}
			
			System.out.println("Your move : " + sMove + "("+moveFrom + "-"+moveTo+")");

		
		}
		print("Goodby...");
	}

	private static void print(String message) {
		// logger.info(message);
		System.out.println(message);
	}
}
