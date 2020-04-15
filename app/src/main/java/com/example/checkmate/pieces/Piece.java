package com.example.checkmate.pieces;

import com.example.checkmate.Square;

import java.util.ArrayList;

public abstract class Piece {
	public char color;
	private boolean dead = false;
	private boolean moved = false;
	String name = "";

	public Piece(char color, boolean dead, boolean moved) {
		super();
		this.color = color;
		this.dead = dead;
		this.moved = moved;
	}


	public char getColor() {
		return this.color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public String setName(String name){
		return this.name=name;
	}

	public String toString() {
		return name;
	}
}
