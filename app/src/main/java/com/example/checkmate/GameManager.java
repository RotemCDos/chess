package com.example.checkmate;

import android.util.Log;
import android.widget.Toast;

import com.example.checkmate.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {

    private GameActivity gA;
    private Square[][] board;

    public GameManager(GameActivity gA) {
        this.board = new Square[8][8];
        this.gA = gA;
    }

    public void restartGame() { // restarts the game
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                this.board[k][l] = new Square(k, l, false, null, false);
            }
        }
        board[0][0] = new Square(0, 0, true, new Rook('b', false, false), false);
        board[0][1] = new Square(0, 1, true, new Knight('b', false, false), false);
        board[0][2] = new Square(0, 2, true, new Bishop('b', false, false), false);
        board[0][3] = new Square(0, 3, true, new Queen('b', false, false), false);
        board[0][4] = new Square(0, 4, true, new King('b', false, false), false);
        board[0][5] = new Square(0, 5, true, new Bishop('b', false, false), false);
        board[0][6] = new Square(0, 6, true, new Knight('b', false, false), false);
        board[0][7] = new Square(0, 7, true, new Rook('b', false, false), false);
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (i == 1) {
                    board[i][j] = new Square(i, j, true, new Pawn('b', false, false), false);
                }
                if (i == 6) {
                    board[i][j] = new Square(i, j, true, new Pawn('w', false, false), false);
                }
            }
        }

        board[7][0] = new Square(7, 0, true, new Rook('w', false, false), false);
        board[7][1] = new Square(7, 1, true, new Knight('w', false, false), false);
        board[7][2] = new Square(7, 2, true, new Bishop('w', false, false), false);
        board[7][3] = new Square(7, 3, true, new Queen('w', false, false), false);
        board[7][4] = new Square(7, 4, true, new King('w', false, false), false);
        board[7][5] = new Square(7, 5, true, new Bishop('w', false, false), false);
        board[7][6] = new Square(7, 6, true, new Knight('w', false, false), false);
        board[7][7] = new Square(7, 7, true, new Rook('w', false, false), false);
    }

    public boolean isOcc(int i, int j) { //checks if the square is occupied
        return board[i][j].isOcc();
    }

    public void setOcc(int i, int j, boolean ok) {
        board[i][j].setOcc(ok);
    }

    public void setPiece(int i, int j, Piece piece) {
        board[i][j].setPiece(piece);
    }

    public boolean isYellow(int i, int j) {
        return board[i][j].isYellow();
    }

    public void setYellow(int i, int j, boolean ok) {
        this.board[i][j].setYellow(ok);
    }

    public Piece getPiece(int i, int j) { // checks what is the piece that's on the square
        return board[i][j].getPiece();
    }

    public boolean isOK(int i, int j) {
        return (i < 8 && i > -1 && j < 8 && j > -1);
    }

    public int getX(int i, int j) {
        return this.board[i][j].getX();
    }

    public int getY(int i, int j) {
        return this.board[i][j].getY();
    }

    public String getString(int i, int j) {
        return getPiece(i, j).toString();
    }

    public boolean isWhiteKing(int i, int j) {
        return (this.board[i][j].getPiece().toString().equals("wk"));
    }

    public boolean isBlackKing(int i, int j) {
        return (this.board[i][j].getPiece().toString().equals("bk"));
    }

    public boolean isRook(int i, int j) {
        return (this.board[i][j].getPiece().toString().equals("wr") || this.board[i][j].getPiece().toString().equals("br"));
    }

    //Update a square

    public void updateSquare(int x, int y, boolean occ, Piece piece, boolean ok) {
        this.board[x][y].setOcc(occ);
        this.board[x][y].setPiece(piece);
        this.board[x][y].setYellow(ok);
    }

    public boolean checkVictor(int i, int j) {
        if (isOK(i, j) && this.board[i][j].isOcc())
            return (this.board[i][j].getPiece().toString().equals("wk") || this.board[i][j].getPiece().toString().equals("bk"));
        return false;
    }

    //Hiding highlited squares

    public void hideAllYellows() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null)
                    setYellow(i, j, false);
            }
    }

    //Checks if a piece can eat another piece

    public void canEat(int i, int j, int x, int y, boolean ok) {
        if (isOK(x, y) && isOK(x, y)) {
            if ((this.board[x][y] != null) && (this.board[x][y].isOcc()) && (this.board[i][j] != null) && (this.board[i][j].isOcc())) {
                if ((this.board[i][j].getPiece().color != this.board[x][y].getPiece().color) && this.board[x][y].getPiece().color != ' ') {
                    setYellow(x, y, ok);
                }
            }
        }
    }

    public void showWhitePiecesMoves(int i, int j, boolean ok, boolean checkBlock) { // Used to show all the possible moves of a certain color
        if(!checkBlock) {
            if (getString(i, j).equals("wp")) {
                pawnAttacks(i, j, ok);
            }
//            if (getString(i, j).equals("wk")) {
//                showKingMoves(i, j, ok);
//            }
        }
        else{
            if (getString(i, j).equals("wp")) {
                showPawnMoves(i, j, ok);
            }
        }
        if (getString(i, j).equals("wr")) {
            showRookMoves(i, j, ok);
        }
        if (getString(i, j).equals("wb")) {
            showBishopMoves(i, j, ok);
        }
        if (getString(i, j).equals("wn")) {
            showKnightMoves(i, j, ok);
        }
        if (getString(i, j).equals("wq")) {
            showQueenMoves(i, j, ok);
        }
    }

    public void showBlackPiecesMoves(int i, int j, boolean ok, boolean checkBlock) { // Used to show all the possible moves of a certain color
        if(!checkBlock) {
            if (getString(i, j).equals("bp")) {
                pawnAttacks(i, j, ok);
            }
//            if (getString(i, j).equals("bk")) {
//                showKingMoves(i, j, ok);
//            }
        }
        else{
            if (getString(i, j).equals("bp")) {
                showPawnMoves(i, j, ok);
            }
        }
        if (getString(i, j).equals("br")) {
            showRookMoves(i, j, ok);
        }
        if (getString(i, j).equals("bb")) {
            showBishopMoves(i, j, ok);
        }
        if (getString(i, j).equals("bn")) {
            showKnightMoves(i, j, ok);
        }
        if (getString(i, j).equals("bq")) {
            showQueenMoves(i, j, ok);
        }
    }

    public void pawnAttacks(int i, int j, boolean ok) {
        if (!board[i][j].getPiece().isMoved()) {
            if (board[i][j].getPiece().color == 'w') {
                if (isOK(i - 1, j - 1)) {
                    setYellow(i - 1, j - 1, ok);
                }
                if (isOK(i - 1, j + 1)) {
                    setYellow(i - 1, j + 1, ok);
                }
            } else {
                if (isOK(i + 1, j - 1)) {
                    setYellow(i + 1, j - 1, ok);
                }
                if (isOK(i + 1, j + 1)) {
                    setYellow(i + 1, j + 1, ok);
                }
            }
        } else {
            if (board[i][j].getPiece().color == 'w') {
                if (isOK(i - 1, j - 1)) {
                    setYellow(i - 1, j - 1, ok);
                }
                if (isOK(i - 1, j + 1)) {
                    setYellow(i - 1, j + 1, ok);
                }
            } else {
                if (isOK(i + 1, j + 1)) {
                    setYellow(i + 1, j + 1, ok);
                }
                if (isOK(i + 1, j - 1)) {
                    setYellow(i + 1, j - 1, ok);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////Pawn Moves

    public void showPawnMoves(int i, int j, boolean ok) {
        if (!board[i][j].getPiece().isMoved()) {
            if (board[i][j].getPiece().color == 'w') {
                if (isOK(i - 2, j) && !this.board[i - 1][j].isOcc()) {
                    if (!this.board[i - 2][j].isOcc()) {
                        setYellow(i - 1, j, ok);
                        setYellow(i - 2, j, ok);
                    } else {
                        setYellow(i - 1, j, ok);
                    }
                }
                canEat(i, j, i - 1, j - 1, ok);
                canEat(i, j, i - 1, j + 1, ok);
            } else {
                if (isOK(i + 2, j) && !this.board[i + 1][j].isOcc()) {
                    if (!this.board[i + 2][j].isOcc()) {
                        setYellow(i + 1, j, ok);
                        setYellow(i + 2, j, ok);
                    } else {
                        setYellow(i + 1, j, ok);
                    }
                }
                canEat(i, j, i + 1, j - 1, ok);
                canEat(i, j, i + 1, j + 1, ok);
            }
        } else {
            if (board[i][j].getPiece().color == 'w') {
                if (isOK(i - 1, j) && !this.board[i - 1][j].isOcc()) {
                    setYellow(i - 1, j, ok);
                }
                canEat(i, j, i - 1, j - 1, ok);
                canEat(i, j, i - 1, j + 1, ok);
            } else {
                if (isOK(i + 1, j) && !this.board[i + 1][j].isOcc()) {
                    setYellow(i + 1, j, ok);
                }
                canEat(i, j, i + 1, j + 1, ok);
                canEat(i, j, i + 1, j - 1, ok);
            }
        }
    }

    /////////////////////////////////////////////////////////// Rook Moves

    public void showRookMoves(int i, int j, boolean ok) {
        if (isOK(i - 1, j)) {
            int x = i;
            while (isOK(x - 2, j) && !this.board[x - 1][j].isOcc()) { //up
                this.board[x - 1][j].setYellow(ok);
                x--;
            }
            if (!this.board[x - 1][j].isOcc()) {
                this.board[x - 1][j].setYellow(ok);
            }
            canEat(i, j, x - 1, j, ok);
            if (isOK(x - 2, j) && !this.board[x - 2][j].isOcc()) {
                if ((this.board[x - 1][j].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x - 1][j].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x - 2][j].setYellow(ok);
                }
            }
        }
        if (isOK(i, j + 1)) {
            int y = j;
            while (isOK(i, y + 2) && !this.board[i][y + 1].isOcc()) { //right
                this.board[i][y + 1].setYellow(ok);
                y++;
            }
            if (!this.board[i][y + 1].isOcc()) {
                this.board[i][y + 1].setYellow(ok);
            }
            canEat(i, j, i, y + 1, ok);
            if (isOK(i, y + 2) && !this.board[i][y + 2].isOcc()) {
                if ((this.board[i][y + 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[i][y + 1].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[i][y + 2].setYellow(ok);
                }
            }
        }
        if (isOK(i + 1, j)) {
            int x = i;
            while (isOK(x + 2, j) && !this.board[x + 1][j].isOcc()) { //down
                this.board[x + 1][j].setYellow(ok);
                x++;
            }
            if (!this.board[x + 1][j].isOcc()) {
                this.board[x + 1][j].setYellow(ok);
            }
            canEat(i, j, x + 1, j, ok);
            if (isOK(x + 2, j) && !this.board[x + 2][j].isOcc()) {
                if ((this.board[x + 1][j].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x + 1][j].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x + 2][j].setYellow(ok);
                }
            }
        }

        if (isOK(i, j - 1)) {
            int y = j;
            while (isOK(i, y - 2) && !this.board[i][y - 1].isOcc()) { //left
                board[i][y - 1].setYellow(ok);
                y--;
            }
            if (!this.board[i][y - 1].isOcc()) {
                board[i][y - 1].setYellow(ok);
            }
            canEat(i, j, i, y - 1, ok);
            if (isOK(i, y - 2) && !this.board[i][y - 2].isOcc()) {
                if ((this.board[i][y - 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[i][y - 1].getPiece().toString().equals("bk")&& this.board[i][j].getPiece().color == 'w')) {
                    board[i][y - 2].setYellow(ok);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////Knight Moves

    public void showKnightMoves(int i, int j, boolean ok) {
        if (isOK(i + 2, j)) {
            if (isOK(i + 2, j - 1)) {
                if (!this.board[i + 2][j - 1].isOcc()) {
                    board[i + 2][j - 1].setYellow(ok);
                }
                canEat(i, j, i + 2, j - 1, ok);
            }
            if (isOK(i + 2, j + 1)) {
                if (!this.board[i + 2][j + 1].isOcc()) {
                    board[i + 2][j + 1].setYellow(ok);
                }
                canEat(i, j, i + 2, j + 1, ok);
            }
        }
        if (isOK(i - 2, j)) {
            if (isOK(i - 2, j - 1)) {
                if (!this.board[i - 2][j - 1].isOcc()) {
                    board[i - 2][j - 1].setYellow(ok);
                }
                canEat(i, j, i - 2, j - 1, ok);
            }
            if (isOK(i - 2, j + 1)) {
                if (!this.board[i - 2][j + 1].isOcc()) {
                    board[i - 2][j + 1].setYellow(ok);
                }
                canEat(i, j, i - 2, j + 1, ok);
            }
        }

        if (isOK(i, j + 2)) {
            if (isOK(i + 1, j + 2)) {
                if (!this.board[i + 1][j + 2].isOcc()) {
                    board[i + 1][j + 2].setYellow(ok);
                } else
                    canEat(i, j, i + 1, j + 2, ok);
            }
            if (isOK(i - 1, j + 2)) {
                if (!this.board[i - 1][j + 2].isOcc()) {
                    board[i - 1][j + 2].setYellow(ok);
                }
                canEat(i, j, i - 1, j + 2, ok);
            }
        }
        if (isOK(i, j - 2)) {
            if (isOK(i + 1, j - 2)) {
                if (!this.board[i + 1][j - 2].isOcc()) {
                    board[i + 1][j - 2].setYellow(ok);
                }
                canEat(i, j, i + 1, j - 2, ok);
            }
            if (isOK(i - 1, j - 2)) {
                if (!this.board[i - 1][j - 2].isOcc()) {
                    board[i - 1][j - 2].setYellow(ok);
                }
                canEat(i, j, i - 1, j - 2, ok);
            }
        }
    }

    ///////////////////////////////////////////////////////////Bishop Moves

    public void showBishopMoves(int i, int j, boolean ok) {
        if (isOK(i - 1, j + 1)) {
            int x = i, y = j;
            while (isOK(x - 2, y + 2) && !this.board[x - 1][y + 1].isOcc()) { //up right
                board[x - 1][y + 1].setYellow(ok);
                y++;
                x--;
            }
            if (!this.board[x - 1][y + 1].isOcc()) {
                board[x - 1][y + 1].setYellow(ok);
            }
            canEat(i, j, x - 1, y + 1, ok);
            if (isOK(x - 2, y + 2) && !this.board[x - 2][y + 2].isOcc()) {
                if ((this.board[x - 1][y + 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x - 1][y + 1].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x - 2][y + 2].setYellow(ok);
                }
            }
        }
        if (isOK(i + 1, j + 1)) {
            int x = i, y = j;
            while (isOK(x + 2, y + 2) && !this.board[x + 1][y + 1].isOcc()) { //right down
                board[x + 1][y + 1].setYellow(ok);
                y++;
                x++;
            }
            if (!this.board[x + 1][y + 1].isOcc()) {
                board[x + 1][y + 1].setYellow(ok);
            }
            canEat(i, j, x + 1, y + 1, ok);
            if (isOK(x + 2, y + 2) && !this.board[x + 2][y + 2].isOcc()) {
                if ((this.board[x + 1][y + 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x + 1][y + 1].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x + 2][y + 2].setYellow(ok);
                }
            }
        }
        if (isOK(i + 1, j - 1)) {
            int x = i, y = j;
            while (isOK(x + 2, y - 2) && !this.board[x + 1][y - 1].isOcc()) { //down left
                board[x + 1][y - 1].setYellow(ok);
                x++;
                y--;
            }
            if (!this.board[x + 1][y - 1].isOcc()) {
                board[x + 1][y - 1].setYellow(ok);
            }
            canEat(i, j, x + 1, y - 1, ok);
            if (isOK(x + 2, y - 2) && !this.board[x + 2][y - 2].isOcc()) {
                if ((this.board[x + 1][y - 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x + 1][y - 1].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x + 2][y - 2].setYellow(ok);
                }
            }
        }

        if (isOK(i - 1, j - 1)) {
            int x = i, y = j;
            while (isOK(x - 2, y - 2) && !this.board[x - 1][y - 1].isOcc()) { //left up
                board[x - 1][y - 1].setYellow(ok);
                y--;
                x--;
            }
            if (!this.board[x - 1][y - 1].isOcc()) {
                board[x - 1][y - 1].setYellow(ok);
            }
            canEat(i, j, x - 1, y - 1, ok);
            if (isOK(x - 2, y - 2) && !this.board[x - 2][y - 2].isOcc()) {
                if ((this.board[x - 1][y - 1].getPiece().toString().equals("wk") && this.board[i][j].getPiece().color == 'b') || (this.board[x - 1][y - 1].getPiece().toString().equals("bk") && this.board[i][j].getPiece().color == 'w')) {
                    board[x - 2][y - 2].setYellow(ok);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////Queen Moves

    public void showQueenMoves(int i, int j, boolean ok) {
        showBishopMoves(i, j, ok);
        showRookMoves(i, j, ok);
    }

    //////////////////////////////////////////////////////////Checking if a square is "touching" two kings

    public boolean checkFor2Kings(int i, int j){
        int check = 0;
        for(int y = j - 1; y < j + 2; y++){
            if (isOK(i + 1, y) && isOcc(i + 1, y)) {
                if(getString(i + 1, y).equals("wk") || getString(i + 1, y).equals("bk")){
                    check++;
                }
            }
        }
        for(int y = j - 1; y < j + 2; y++){
            if (isOK(i, y) && isOcc(i, y) && y != j) {
                if(getString(i, y).equals("wk") || getString(i, y).equals("bk")){
                    check++;
                }
            }
        }
        for(int y = j - 1; y < j + 2; y++){
            if (isOK(i - 1, y) && isOcc(i - 1, y)) {
                if(getString(i - 1, y).equals("wk") || getString(i - 1, y).equals("bk")){
                    check++;
                }
            }
        }
        return (check < 2);
    }
    ///////////////////////////////////////////////////////////King Moves

    public void showKingMoves(int i, int j, boolean ok) {
        castlingMarkings(this.board[i][j].getPiece().color);
        int y = j - 1;
        while (y < j + 2) {
            if (isOK(i + 1, y)) {
                if (!this.board[i + 1][y].isOcc() && checkFor2Kings(i + 1, y)) {
                    board[i + 1][y].setYellow(ok);
                }
                if (this.board[i + 1][y].isOcc() && checkFor2Kings(i + 1, y)) {
                    if (this.board[i + 1][y].getPiece().color != this.board[i][j].getPiece().color) {
                        if (this.board[i + 1][y].getPiece().color == 'w') {
                            this.board[i + 1][y].getPiece().setColor('b');
                            gA.blackKingThreat(true);
                            if (!this.board[i + 1][y].isYellow()) {
                                this.board[i + 1][y].getPiece().setColor('w');
                                canEat(i, j, i + 1, y, ok);
                            }
                            gA.blackKingThreat(false);
                            this.board[i + 1][y].getPiece().setColor('w');
                        } else {
                            this.board[i + 1][y].getPiece().setColor('w');
                            gA.whiteKingThreat(true);
                            if (!this.board[i + 1][y].isYellow()) {
                                this.board[i + 1][y].getPiece().setColor('b');
                                canEat(i, j, i + 1, y, ok);
                            }
                            gA.whiteKingThreat(false);
                            this.board[i + 1][y].getPiece().setColor('b');
                        }
                    }
                }
            }
            y++;
        }

        y = j - 1;
        while (y < j + 2) {
            if (isOK(i, y)) {
                if (!this.board[i][y].isOcc() && y != j && checkFor2Kings(i, y)) {
                    board[i][y].setYellow(ok);
                }
                if (this.board[i][y].isOcc() && checkFor2Kings(i, y)) {
                    if (this.board[i][y].getPiece().color != this.board[i][j].getPiece().color) {
                        if (this.board[i][y].getPiece().color == 'w') {
                            this.board[i][y].getPiece().setColor('b');
                            gA.blackKingThreat(true);
                            if (!this.board[i][y].isYellow()) {
                                this.board[i][y].getPiece().setColor('w');
                                canEat(i, j, i, y, ok);
                            }
                            gA.blackKingThreat(false);
                            this.board[i][y].getPiece().setColor('w');
                        } else {
                            this.board[i][y].getPiece().setColor('w');
                            gA.whiteKingThreat(true);
                            if (!this.board[i][y].isYellow()) {
                                this.board[i][y].getPiece().setColor('b');
                                canEat(i, j, i, y, ok);
                            }
                            gA.whiteKingThreat(false);
                            this.board[i][y].getPiece().setColor('b');
                        }
                    }
                }
            }
            y++;
        }

        y = j - 1;
        while (y < j + 2) {
            if (isOK(i - 1, y)) {
                if (!this.board[i - 1][y].isOcc() && checkFor2Kings(i - 1, y)) {
                    board[i - 1][y].setYellow(ok);
                }
                if (this.board[i - 1][y].isOcc() && checkFor2Kings(i - 1, y)) {
                    if (this.board[i - 1][y].getPiece().color != this.board[i][j].getPiece().color) {
                        if (this.board[i - 1][y].getPiece().color == 'w') {
                            this.board[i - 1][y].getPiece().setColor('b');
                            gA.blackKingThreat(true);
                            if (!this.board[i - 1][y].isYellow()) {
                                this.board[i - 1][y].getPiece().setColor('w');
                                canEat(i, j, i - 1, y, ok);
                            }
                            gA.blackKingThreat(false);
                            this.board[i - 1][y].getPiece().setColor('w');
                        } else {
                            this.board[i - 1][y].getPiece().setColor('w');
                            gA.whiteKingThreat(true);
                            if (!this.board[i - 1][y].isYellow()) {
                                this.board[i - 1][y].getPiece().setColor('b');
                                canEat(i, j, i - 1, y, ok);
                            }
                            gA.whiteKingThreat(false);
                            this.board[i - 1][y].getPiece().setColor('b');
                        }
                    }
                }
            }
            y++;
        }
    }

    public boolean canBlockThreat(Piece piece){
        boolean ok = false;
        if(piece.toString().matches("wr|wb|wq|bb|br|bq")) {
            if (piece.color == 'w') {
                for (int o = 0; o < 2; o++) {
                    for (int p = 0; p < 8; p++) {
                        for (int k = 0; k < 8; k++) {
                            if (o == 0) {
                                if (this.board[p][k] != null && this.board[p][k].isOcc()) {
                                    showBlackPiecesMoves(p, k, true, true);
                                }
                            } else {
                                if (this.board[p][k].isYellow()) {
                                    this.board[p][k].setOcc(true);
                                    this.board[p][k].setYellow(false);
                                    this.board[p][k].setPiece(new EmptyPiece());
                                }
                            }
                        }
                    }
                }
                if (piece.toString() == "wb") {
                    showBishopMoves(gA.x, gA.y, true);
                    ok = !this.board[gA.blackKX][gA.blackKY].isYellow();
                } else {
                    if (piece.toString() == "wr") {
                        showRookMoves(gA.x, gA.y, true);
                        ok = !this.board[gA.blackKX][gA.blackKY].isYellow();
                    } else {
                        showQueenMoves(gA.x, gA.y, true);
                        ok = !this.board[gA.blackKX][gA.blackKY].isYellow();
                    }
                }
            }
            else{
                for (int o = 0; o < 2; o++) {
                    for (int p = 0; p < 8; p++) {
                        for (int k = 0; k < 8; k++) {
                            if (o == 0) {
                                if (this.board[p][k] != null && this.board[p][k].isOcc()) {
                                    showWhitePiecesMoves(p, k, true, true);
                                }
                            } else {
                                if (this.board[p][k].isYellow() && !this.board[p][k].isOcc()) {
                                    this.board[p][k].setOcc(true);
                                    this.board[p][k].setYellow(false);
                                    this.board[p][k].setPiece(new EmptyPiece());
                                }
                            }
                        }
                    }
                }
                if (piece.toString() == "bb") {
                    showBishopMoves(gA.x, gA.y, true);
                    ok = !this.board[gA.whiteKX][gA.whiteKY].isYellow();
                } else {
                    if (piece.toString() == "br") {
                        showRookMoves(gA.x, gA.y, true);
                        ok = !this.board[gA.whiteKX][gA.whiteKY].isYellow();
                    } else {
                        showQueenMoves(gA.x, gA.y, true);
                        ok = !this.board[gA.whiteKX][gA.whiteKY].isYellow();
                    }
                }
            }
        }
        for (int p = 0; p < 8; p++) {
            for (int k = 0; k < 8; k++) {
                if (this.board[p][k].isOcc() && this.board[p][k].getPiece().toString().equals("EmptyPiece")){
                    this.board[p][k].setOcc(false);
                    this.board[p][k].setPiece(null);
                }
            }
        }
        return ok;
    }

    public boolean canRemoveThreat(char color) {
        boolean ok = false;
        int threat = 0;
        if (color == 'w') {
            for (int p = 0; p < 8; p++)
                for (int k = 0; k < 8; k++) {
                    if (this.board[p][k] != null && this.board[p][k].isOcc()) {
                        showBlackPiecesMoves(p, k, true, false);
                        if (this.board[gA.whiteKX][gA.whiteKY].isYellow()) {
                            threat++;
                            gA.savePiece(p, k);
                        }
                        hideAllYellows();
                    }
                }
            if (threat < 2) {
                gA.blackKingThreat(true);
                if (this.board[gA.x][gA.y].isYellow()) {
                    ok = true;
                }
                else{
                    hideAllYellows();
                    ok = canBlockThreat(gA.piece);
                }
                hideAllYellows();
            }
        } else {
            for (int p = 0; p < 8; p++)
                for (int k = 0; k < 8; k++) {
                    if (this.board[p][k] != null && this.board[p][k].isOcc()) {
                        showWhitePiecesMoves(p, k, true, false);
                        if (this.board[gA.blackKX][gA.blackKY].isYellow()) {
                            threat++;
                            gA.savePiece(p, k);
                        }
                        hideAllYellows();
                    }
                }
            if (threat < 2) {
                gA.whiteKingThreat(true);
                if (this.board[gA.x][gA.y].isYellow()) {
                    ok = true;
                }
                else{
                    hideAllYellows();
                    ok = canBlockThreat(gA.piece);
                }
                hideAllYellows();
            }
        }
        return ok;
    }


    public boolean foundYellowSquares() { // Checks all the squares to find yellow squares
        boolean yellowExist = false;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null && isYellow(i, j)) {
                    yellowExist = true;
                }
            }
        hideAllYellows();
        return yellowExist;
    }

    public void victoryCheck(char color) {
        char c = ' ';
        if (color == 'w') {
            gA.whiteKingThreat(true);
            showKingMoves(gA.whiteKX, gA.whiteKY, true);
            gA.whiteKingThreat(false);
            if (!foundYellowSquares() && gA.whiteThreat && !canRemoveThreat(color)) {
                c = 'b';
                gA.finishGame(c);
            }
        }
        else {
            gA.blackKingThreat(true);
            showKingMoves(gA.blackKX, gA.blackKY, true);
            gA.blackKingThreat(false);
            if (!foundYellowSquares() && gA.blackThreat && !canRemoveThreat(color)) {
                c = 'w';
                gA.finishGame(c);
            }
        }
        hideAllYellows();
    }

    public Piece promotePawn(int i, char color, String pieceStr) { // Sets a pawn that reached the first / eighth rank to a different piece
        if (i == 0 || i == 7) {
            if (pieceStr.equals("r")) {
                return new Rook(color, false, true);
            }
            if (pieceStr.equals("n")) {
                return new Knight(color, false, true);
            }
            if (pieceStr.equals("b")) {
                return new Bishop(color, false, true);
            }
            if (pieceStr.equals("q")) {
                return new Queen(color, false, true);
            }
            gA.setBoardClickable(false);
        } else {
            gA.setBoardClickable(true);
        }
        return new Pawn(color, false, true);
    }

    public void castlingMarkings(char color) {
        if(color == 'w') {
            if(this.board[7][0].isOcc() && this.board[7][4].isOcc()) {
                if (!this.board[7][0].getPiece().isMoved() && !this.board[7][4].getPiece().isMoved()) {
                    if (!this.board[7][1].isOcc() && !this.board[7][2].isOcc() && !this.board[7][3].isOcc()) {
                        this.board[7][2].setYellow(true);
                        this.board[7][0].getPiece().setDead(true);
                        this.board[7][4].getPiece().setDead(true);
                    }
                }
            }
            if(this.board[7][7].isOcc() && this.board[7][4].isOcc()) {
                if (!this.board[7][7].getPiece().isMoved() && !this.board[7][4].getPiece().isMoved()) {
                    if (!this.board[7][5].isOcc() && !this.board[7][6].isOcc()) {
                        this.board[7][6].setYellow(true);
                        this.board[7][7].getPiece().setDead(true);
                        this.board[7][4].getPiece().setDead(true);
                    }
                }
            }
        }
        else{
            if(this.board[0][0].isOcc() && this.board[0][4].isOcc()) {
                if (!this.board[0][0].getPiece().isMoved() && !this.board[0][4].getPiece().isMoved()) {
                    if (!this.board[0][1].isOcc() && !this.board[0][2].isOcc() && !this.board[0][3].isOcc()) {
                        this.board[0][2].setYellow(true);
                        this.board[0][0].getPiece().setDead(true);
                        this.board[0][4].getPiece().setDead(true);
                    }
                }
            }
            if(this.board[0][7].isOcc() && this.board[0][4].isOcc()) {
                if (!this.board[0][7].getPiece().isMoved() && !this.board[0][4].getPiece().isMoved()) {
                    if (!this.board[0][5].isOcc() && !this.board[0][6].isOcc()) {
                        this.board[0][6].setYellow(true);
                        this.board[0][7].getPiece().setDead(true);
                        this.board[0][4].getPiece().setDead(true);
                    }
                }
            }
        }
    }

    public void doCastling(char color){
        if(color == 'w'){
            if (this.board[7][0].getPiece().isDead()){
                updateSquare(7, 3, true, this.board[7][0].getPiece() , false);
                updateSquare(7, 0, false, null, false);
                this.board[7][3].getPiece().setMoved(true);
                this.board[7][3].getPiece().setDead(false);
                this.board[7][2].getPiece().setDead(false);
            }
            else
                if (this.board[7][7].getPiece().isDead()){
                updateSquare(7, 5, true, this.board[7][7].getPiece() , false);
                updateSquare(7, 7, false, null, false);
                this.board[7][5].getPiece().setMoved(true);
                this.board[7][5].getPiece().setDead(false);
                this.board[7][6].getPiece().setDead(false);
            }
        }
        else{
            if (this.board[0][0].getPiece().isDead()){
                updateSquare(0, 3, true, this.board[0][0].getPiece() , false);
                updateSquare(0, 0, false, null, false);
                this.board[0][3].getPiece().setMoved(true);
                this.board[0][3].getPiece().setDead(false);
                this.board[0][2].getPiece().setDead(false);
            }
            else
            if (this.board[0][7].getPiece().isDead()){
                updateSquare(0, 5, true, this.board[0][7].getPiece() , false);
                updateSquare(0, 7, false, null, false);
                this.board[0][5].getPiece().setMoved(true);
                this.board[0][5].getPiece().setDead(false);
                this.board[0][6].getPiece().setDead(false);
            }
        }
    }
}