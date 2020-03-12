package com.example.checkmate;

import com.example.checkmate.pieces.*;

public class Square {
	private int x,y;
	private Piece piece;
	private boolean occ, isYellow;
	
	public Square(int x, int y, boolean occ, Piece piece, boolean yellow)
	{
		this.x = x;
		this.y = y;
		this.occ = occ;
		this.piece = piece;
		this.isYellow = yellow;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isOcc() {
		return occ;
	}

	public void setOcc(boolean occ) {
		this.occ = occ;
	}

	public void setPiece(Piece piece){
		this.piece = piece;
	}

	public Piece getPiece(){
		return this.piece;
	}

	public boolean isYellow(){
		return this.isYellow;
	}

	public void setYellow(boolean yellow){
		this.isYellow = yellow;
	}
}
