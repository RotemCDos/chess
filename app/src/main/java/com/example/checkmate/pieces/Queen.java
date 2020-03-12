package com.example.checkmate.pieces;

public class Queen extends Piece{
	
	public Queen(char color, boolean dead, boolean moved) {
		super(color, dead, moved);
	}


	public String toString() {
		return color + "q";
	}
}
