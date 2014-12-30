ralphs-chess-machine
====================

Just a simple chess computer programm....

The idea of this project is a simple java based headless chess-computer programm. I want to see how such a software could work. It's more an experiment. But maybe it becomes some day more....


How to play
------------

Start a new Game by running the Game class

    java -cp ralphs-chess-maschine-0.3.0.jar com.soika.chess.Game
    *************************
	* Ralphs Chess Maschine *
	* V0.1.0                *
	*************************
	Setup the board...
	Lets play...
	         A B C D E F G H
	        ┌───────────────┐
	      8 │♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜│
	      7 │♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟│
	      6 │               │
	      5 │               │
	      4 │               │
	      3 │               │
	      2 │♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙│
	      1 │♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖│
	        └───────────────┘
	ChessMashine analyzing depth=2
    

Just enter your move the following form and press enter:

    E2E4

and press enter. 

	Please enter your move : e2e4
	Start analyizing 20 possible moves.....
	let me think just a second....
	.....13721 szeanrios analyzed in 4112ms
	.....14 seemingly reasonable moves found.
	         A B C D E F G H
	        ┌───────────────┐
	      8 │♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜│
	      7 │♟ ♟ ♟ ♟ ♟ ♟   ♟│
	      6 │            ♟  │
	      5 │               │
	      4 │        ♙      │
	      3 │               │
	      2 │♙ ♙ ♙ ♙   ♙ ♙ ♙│
	      1 │♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖│
	        └───────────────┘
	               My move : G7G6  (Rating=0 Depth=2)
	Please enter your move : 


To quit the game enter 'q'

To print the board enter 'p'

To change the Debug level enter 'l1' 'l2' or 'l0'
  