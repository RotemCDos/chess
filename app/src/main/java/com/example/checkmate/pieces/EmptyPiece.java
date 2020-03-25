package com.example.checkmate.pieces;

import com.example.checkmate.Square;

public class EmptyPiece extends Piece{

    public EmptyPiece() {
        super(' ', false, false);
    }


    public String toString() {
        return "EmptyPiece";
    }

}
