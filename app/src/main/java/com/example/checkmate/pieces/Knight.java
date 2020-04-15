package com.example.checkmate.pieces;

public class Knight extends Piece{

	public Knight(char color, boolean dead, boolean moved) {
		super(color, dead, moved);
	}

	public String toString() {
		return color + "n";
	}

}
