package com.example.checkmate.pieces;

public class King extends Piece{
	
	public King(char color, boolean dead, boolean moved) {
		super(color, dead, moved);
	}


	public String toString() {
		return color + "k";
	}
}
