package com.soika.chess;

public class Move {

	
	byte from;
	byte to;
	byte targetFigure;
	
	Move[] reactions;
	

	/**
	 * Contstructor stores move and target figure
	 * @param board
	 * @param from
	 * @param to
	 */
	public Move(Board board, byte from, byte to) {
		super();
		
		this.targetFigure=board.getFigure(to);
		this.from=from;
		this.to=to;
		
	}


	public Move[] getReactions() {
		return reactions;
	}


	public void setReactions(Move[] reactions) {
		this.reactions = reactions;
	}
	
	
	
}
