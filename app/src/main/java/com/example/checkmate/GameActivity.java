package com.example.checkmate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.example.checkmate.pieces.Pawn;
import com.example.checkmate.pieces.Piece;
import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    String name1 = "*****", name2 = "$$$$$$";

    private TextView tvPlayer1, tvPlayer2;
    private GameManager gM;
    private ImageButton btnBack;
    private ImageButton btnRes;
    private ImageButton[][] arr;
    private ImageButton[] wPieceR, bPieceR;
    int[] wPieceArr = {R.drawable.pawnw , R.drawable.rookw, R.drawable.knightw, R.drawable.bishopw, R.drawable.queenw, R.drawable.kingw};
    int[] bPieceArr = {R.drawable.pawnb , R.drawable.rookb, R.drawable.knightb, R.drawable.bishopb, R.drawable.queenb, R.drawable.kingb};
    int[] squareseArr = {R.drawable.brownl , R.drawable.brownd, R.drawable.yellowsquare};

    public boolean yellowExist = false;
    public boolean isWhiteTurn = true;
    public boolean whiteThreat, blackThreat;
    boolean ok, isGameOver;
    int i, j, resId , x , y;
    Piece piece = null, piece2 = null;
    String sb = "";
    int whiteKX = 7, whiteKY = 4, blackKX = 0, blackKY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_game);

        gM = new GameManager(this);

        btnBack = (ImageButton) findViewById(R.id.btnBackg);
        btnBack.setOnClickListener(this);

        btnRes = (ImageButton) findViewById(R.id.btnRes);
        btnRes.setOnClickListener(this);

        tvPlayer1 = (TextView) findViewById(R.id.tvName1);
        tvPlayer2 = (TextView) findViewById(R.id.tvName2);

        piece = null;
        piece2 = null;

        isGameOver = false;
        whiteThreat = false;
        blackThreat = false;

        name1 = enterinfo.name1;
        name2 = enterinfo.name2;

        tvPlayer1.setText(name1);
        tvPlayer2.setText(name2);

        wPieceR = new ImageButton[4];
        bPieceR = new ImageButton[4];

        arr = new ImageButton[8][8];
        String str = "";

        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr.length; j++) {
                switch (i) {
                    default:
                        str = "img" + i + j;
                        break;
                    case 1:
                        str = "pawnb" + j;
                        break;
                    case 6:
                        str = "pawnw" + j;
                        break;
                    case 0:
                        switch (j) {
                            case 0:
                            case 7:
                                str = "rookb" + j;
                                break;
                            case 1:
                            case 6:
                                str = "knightb" + j;
                                break;
                            case 2:
                            case 5:
                                str = "bishopb" + j;
                                break;
                            case 3:
                                str = "queenb";
                                break;
                            case 4:
                                str = "kingb";
                                break;
                        }
                        break;
                    case 7:
                        switch (j) {
                            case 0:
                            case 7:
                                str = "rookw" + j;
                                break;
                            case 1:
                            case 6:
                                str = "knightw" + j;
                                break;
                            case 2:
                            case 5:
                                str = "bishopw" + j;
                                break;
                            case 3:
                                str = "queenw";
                                break;
                            case 4:
                                str = "kingw";
                                break;
                        }

                }

                resId = getResources().getIdentifier(str, "id", getPackageName());
                arr[i][j] = (ImageButton) findViewById(resId);
                arr[i][j].setOnClickListener(this);

                wPieceR[0] = (ImageButton) findViewById(R.id.wq);
                wPieceR[0].setOnClickListener(this);
                wPieceR[1] = (ImageButton) findViewById(R.id.wn);
                wPieceR[1].setOnClickListener(this);
                wPieceR[2] = (ImageButton) findViewById(R.id.wr);
                wPieceR[2].setOnClickListener(this);
                wPieceR[3] = (ImageButton) findViewById(R.id.wb);
                wPieceR[3].setOnClickListener(this);

                bPieceR[0] = (ImageButton) findViewById(R.id.bq);
                bPieceR[0].setOnClickListener(this);
                bPieceR[1] = (ImageButton) findViewById(R.id.bn);
                bPieceR[1].setOnClickListener(this);
                bPieceR[2] = (ImageButton) findViewById(R.id.br);
                bPieceR[2].setOnClickListener(this);
                bPieceR[3] = (ImageButton) findViewById(R.id.bb);
                bPieceR[3].setOnClickListener(this);
            }
        }
        restartGame();
    }

    public void restartGame() { // Restarts the game
        gM.restartGame();
        setSquaresImage();
        isWhiteTurn = true;
        setBoardClickable(true);
        setWhiteReplace(false);
        setBlackReplace(false);
        isGameOver = false;
        piece = null;
        piece2 = null;
        whiteThreat = false;
        blackThreat = false;
        whiteKX = 7;
        whiteKY = 4;
        blackKX = 0;
        blackKY = 4;
    }

    public void setWhiteReplace(boolean ok){ // Sets the pawn-promotion white board clickable
        for (int i = 0; i < 4; i++) {
            wPieceR[i].setClickable(ok);
        }
    }

    public void setBlackReplace(boolean ok){ // Sets the pawn-promotion black board clickable
        for (int i = 0; i < 4; i++) {
            bPieceR[i].setClickable(ok);
        }
    }

    public void setBoardClickable(boolean ok){ // Sets the chess board clickable
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j].setClickable(ok);
            }
        }
    }

    public boolean whiteKingThreat(boolean ok){ // Checks if the white king is threatened
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((arr[i][j] != null) && gM.isOcc(i,j)) {
                    if (gM.getPiece(i, j).color == 'b') {
                        gM.showBlackPiecesMoves(i, j, ok, false);
                    }
                    if(gM.getString(i,j).equals("wk")) {
                        whiteKX = i;
                        whiteKY = j;
                    }
                }
            }
        }
        return gM.isYellow(whiteKX, whiteKY);
    }

    public boolean blackKingThreat(boolean ok){ // Checks if the black king is threatened
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(arr[i][j] != null && gM.isOK(i,j) && gM.isOcc(i,j)) {
                    if (gM.getPiece(i, j).color == 'w') {
                        gM.showWhitePiecesMoves(i, j, ok, false);
                    }
                    if(gM.getString(i,j).equals("bk")) {
                        blackKX = i;
                        blackKY = j;
                    }
                }
            }
        }
        return gM.isYellow(blackKX, blackKY);
    }

    public void checkKing(){ // Checks if a king is threatened
        whiteThreat = whiteKingThreat(true);
        gM.hideAllYellows();

        blackThreat = blackKingThreat(true);
        gM.hideAllYellows();
    }

    public void moveToYellow(int x, int y, Piece piece){ // Moves a piece to chosen place on the board
        boolean occ = false;
        piece2 = piece;
        if(arr[i][j] != null && gM.isOcc(i,j)) {
            piece = gM.getPiece(i, j);
            occ = true;
        }
        else {
            piece = null;
        }
            gM.updateSquare(i, j, true, piece2, false);
            gM.updateSquare(x, y, false, null, false);
            checkKing();
            if((isWhiteTurn && whiteThreat) || (!isWhiteTurn && blackThreat)){
                gM.updateSquare(i, j, occ, piece, false);
                gM.updateSquare(x, y, true, piece2, false);
                Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vi.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else {
                    vi.vibrate(100);
                }
            }
            else{
                isWhiteTurn = !isWhiteTurn;
                piece2.setMoved(true);

                if(gM.getPiece(i,j).isDead()){
                    gM.doCastling(gM.getPiece(i,j).color);
                }

                savePiece(i, j);
                gM.hideAllYellows();
                setSquaresImage();
                pawnAtEnd();

                gM.victoryCheck('w');
                gM.victoryCheck('b');
            }
    }

    public void finishGame(char c){ // Stops the game
        setBoardClickable(false);
        setWhiteReplace(false);
        setBlackReplace(false);
        isGameOver = true;

        if(c == 'b'){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Black Wins!")
                    .setNeutralButton("Main Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent;
                            MainActivity.music.pause();
                            intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setCancelable(false)
                    .show();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00FFFF"));
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#00FFFF"));
        }
        else {
            if (c == 'w') {
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("White Wins!")
                        .setNeutralButton("Main Menu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent;
                                MainActivity.music.pause();
                                intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        })
                        .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartGame();
                            }
                        })
                        .setCancelable(false)
                        .show();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#0000FF"));
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#0000FF"));
            }
        }
    }

    public void setYellowSquares() { // Sets the square which a piece can move to to a yellow square
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (arr[i][j] != null && gM.isYellow(i, j)) {
                    if (gM.isOcc(i, j)) {
                        arr[i][j].setBackgroundResource(squareseArr[2]);
                        yellowExist = true;
                    }
                    else {
                        arr[i][j].setImageResource(squareseArr[2]);
                        yellowExist = true;
                    }
                }
            }
    }

    public void setPieceVisibleW(boolean ok){ // Sets the pawn-promotion white board visible
        for(int k = 0; k < 4; k++) {
            if (ok) {
                wPieceR[k].setVisibility(View.VISIBLE);
            }
            if(!ok){
                wPieceR[k].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setPieceVisibleB(boolean ok){ // Sets the pawn-promotion black board visible
        for(int k = 0; k < 4; k++) {
            if (ok) {
                bPieceR[k].setVisibility(View.VISIBLE);
            }
            if(!ok){
                bPieceR[k].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void pawnAtEnd(){ // Checks if a pawn has reached the first or eighth rank
        if(!isGameOver) {
            if(arr[x][y] != null && gM.isOcc(x,y)) {
                if (x == 0) {
                    if (gM.getString(x, y).equals("wp")) {
                        setBoardClickable(false);
                        setWhiteReplace(true);
                        setPieceVisibleW(true);
                    }
                }
                if (x == 7) {
                    if (gM.getString(x, y).equals("bp")) {
                        setBoardClickable(false);
                        setBlackReplace(true);
                        setPieceVisibleB(true);
                    }
                }
            }
        }
    }

    public void savePiece(int i, int j){ // Saves a piece for later use
        this.x = gM.getX(i,j);
        this.y = gM.getY(i,j);
        this.piece = gM.getPiece(i,j);
    }

    public void setSquaresImage(){ // Sets all the images on board to squares or pieces
        yellowExist = false;
        setPieceVisibleW(false);
        setPieceVisibleB(false);
        for (int k = 0; k < 8; k++)
            for (int p = 0; p < 8; p++) {
                if (arr[k][p] != null) {
                        if (k % 2 == 0) {
                            if (p % 2 == 0) {
                                arr[k][p].setImageResource(squareseArr[0]);
                                arr[k][p].setBackgroundResource(squareseArr[0]);
                            }
                            else {
                                arr[k][p].setImageResource(squareseArr[1]);
                                arr[k][p].setBackgroundResource(squareseArr[1]);
                            }
                        } else if (p % 2 == 0) {
                            arr[k][p].setImageResource(squareseArr[1]);
                            arr[k][p].setBackgroundResource(squareseArr[1]);
                        }
                        else {
                            arr[k][p].setImageResource(squareseArr[0]);
                            arr[k][p].setBackgroundResource(squareseArr[0]);
                        }
                    if(gM.isOcc(k,p)){
                        if (gM.getPiece(k, p).toString().equals("wp")) {
                            arr[k][p].setImageResource(wPieceArr[0]);
                        }
                        if (gM.getPiece(k, p).toString().equals("bp")) {
                            arr[k][p].setImageResource(bPieceArr[0]);
                        }
                        if (gM.getPiece(k, p).toString().equals("wr")) {
                            arr[k][p].setImageResource(wPieceArr[1]);
                        }
                        if (gM.getPiece(k, p).toString().equals("wb")) {
                            arr[k][p].setImageResource(wPieceArr[3]);
                        }
                        if (gM.getPiece(k, p).toString().equals("wn")) {
                            arr[k][p].setImageResource(wPieceArr[2]);
                        }
                        if (gM.getPiece(k, p).toString().equals("wq")) {
                            arr[k][p].setImageResource(wPieceArr[4]);
                        }
                        if (gM.getPiece(k, p).toString().equals("wk")) {
                            arr[k][p].setImageResource(wPieceArr[5]);
                        }
                        if (gM.getPiece(k, p).toString().equals("br")) {
                            arr[k][p].setImageResource(bPieceArr[1]);
                        }
                        if (gM.getPiece(k, p).toString().equals("bb")) {
                            arr[k][p].setImageResource(bPieceArr[3]);
                        }
                        if (gM.getPiece(k, p).toString().equals("bn")) {
                            arr[k][p].setImageResource(bPieceArr[2]);
                        }
                        if (gM.getPiece(k, p).toString().equals("bq")) {
                            arr[k][p].setImageResource(bPieceArr[4]);
                        }
                        if (gM.getPiece(k, p).toString().equals("bk")) {
                            arr[k][p].setImageResource(bPieceArr[5]);
                        }
                    }
                }
            }
    }

    public void setNewPiece(View v){ // Checks which pawn-promotion piece is clicked and sets the pawn to the piece
        if(i < 4){
            if(v.getId() == wPieceR[i].getId()){
                if(i == 0)
                    sb = "q";
                if(i == 1)
                    sb = "n";
                if(i == 2)
                    sb = "r";
                if(i == 3)
                    sb = "b";
                gM.setPiece(x, y, gM.promotePawn(x, gM.getPiece(x,y).getColor(), sb));
//                checkKing();
                gM.victoryCheck('w');
            }
            if(v.getId() == bPieceR[i].getId()){
                if(i == 0)
                    sb = "q";
                if(i == 1)
                    sb = "n";
                if(i == 2)
                    sb = "r";
                if(i == 3)
                    sb = "b";
                gM.setPiece(x, y, gM.promotePawn(x, gM.getPiece(x,y).getColor(), sb));
//                checkKing();
                gM.victoryCheck('b');
            }
        }
        setBoardClickable(true);
        setBlackReplace(false);
        setWhiteReplace(false);
        setSquaresImage();
    }

    public void whiteMovement(int i, int j){ // Possible white pieces moves
        if (gM.getString(i, j).equals("wp")) {
            gM.showPawnMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("wr")) {
            gM.showRookMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("wb")) {
            gM.showBishopMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("wn")) {
            gM.showKnightMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("wq")) {
            gM.showQueenMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("wk")) {
            whiteKingThreat(true);
            gM.showKingMoves(i, j, true);
            whiteKingThreat(false);
//            gM.victoryCheck('w');
//            gM.castling();
        }
    }

    public void blackMovement(int i, int j){ // Possible black pieces moves
        if (gM.getString(i, j).equals("bp")) {
            gM.showPawnMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("br")) {
            gM.showRookMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("bb")) {
            gM.showBishopMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("bn")) {
            gM.showKnightMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("bq")) {
            gM.showQueenMoves(i, j, true);
        }
        if (gM.getString(i, j).equals("bk")) {
            blackKingThreat(true);
            gM.showKingMoves(i, j, true);
            blackKingThreat(false);
//            gM.victoryCheck('b');
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnBackg:
                MainActivity.music.pause();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRes:
                restartGame();
                break;
            default:
                setSquaresImage();
                ok = false;
                for (i = 0; i < 8 && !ok; i++) {
                    setNewPiece(v);
                    for (j = 0; j < 8 && !ok; j++) {
                        if (v.getId() == arr[i][j].getId()) {
                            ok = true;
                            if (gM.isOcc(i, j) && gM.getPiece(i, j) != null && !gM.isYellow(i, j)) {
                                gM.hideAllYellows();
                                if(isWhiteTurn) {
                                    whiteMovement(i,j);
                                } else{
                                    blackMovement(i,j);
                                }
                                savePiece(i, j);
                                setYellowSquares();
                            } else {
                                if (gM.isYellow(i, j)) {
                                    moveToYellow(this.x, this.y,this.piece);
                                } else {
                                    gM.hideAllYellows();
                                }
                            }

                        }

                    }
                }
        }
    }
}