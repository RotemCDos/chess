package chess;

import com.example.checkmate.Square;
import com.example.checkmate.pieces.*;

public class Board {
	private Square[][] squares = new Square[8][8];

	public Board(Piece piece)
	{
		for(int i = 0; i < this.squares.length;i++)
		{
			for(int j = 0; j < this.squares.length;j++)
			{
				if(i==0 || i==1 || i==6 || i==7)
				{
					this.squares[i][j].setOcc(true);
					this.squares[i][j].setPiece(piece);
				}
				else
					this.squares[i][j].setOcc(false);
			}
		}
	}
}
