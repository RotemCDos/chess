package com.example.checkmate.pieces;

public class Rook extends Piece{
	
	public Rook(char color, boolean dead, boolean moved) {
		super(color, dead, moved);
	}


	public String toString() {
		return color + "r";
	}
}
