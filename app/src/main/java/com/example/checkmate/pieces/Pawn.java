package com.example.checkmate.pieces;

import com.example.checkmate.Square;

public class Pawn extends Piece{

	public Pawn(char color, boolean dead, boolean moved) {
		super(color, dead, moved);
	}


	public String toString() {
		return color + "p";
	}

}
