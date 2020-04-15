package com.example.checkmate.pieces;

public class Bishop extends Piece{

	public Bishop(char color, boolean dead, boolean moved) {
		super(color, dead, moved);

	}

	public String toString() {
		return color + "b";
	}
}
