package src.engine.eval;

import src.engine.BitBoard;
import src.engine.Type.PlayerColor;

public class Evaluation {
    int PAWN = 1;
    int KNIGHT = 3;
    int BISHOP = 3;
    int ROOK = 5;
    int QUEEN = 9;
    int KING = 1000;
    BitBoard bitBoard;

    public Evaluation(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }
    
    private static final int[] pawnTable = {
        0,  0,  0,  0,  0,  0,  0,  0,
        5, 10, 10,-20,-20, 10, 10,  5,
        5, -5,-10,  0,  0,-10, -5,  5,
        0,  0,  0, 20, 20,  0,  0,  0,
        5,  5, 10, 25, 25, 10,  5,  5,
       10, 10, 20, 30, 30, 20, 10, 10,
       50, 50, 50, 50, 50, 50, 50, 50,
        0,  0,  0,  0,  0,  0,  0,  0
    };

    private static final int[] knightTable = {
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -30,  0, 10, 15, 15, 10,  0,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  0, 15, 20, 20, 15,  0,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50
    };

    private static final int[] bishopTable = {
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    };

    private static final int[] rookTable = {
        0,  0,  0,  0,  0,  0,  0,  0,
        5, 10, 10, 10, 10, 10, 10,  5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        0,  0,  0,  5,  5,  0,  0,  0
    };

    private static final int[] queenTable = {
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5,  5,  5,  5,  0,-10,
         -5,  0,  5,  5,  5,  5,  0, -5,
          0,  0,  5,  5,  5,  5,  0, -5,
        -10,  5,  5,  5,  5,  5,  0,-10,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    };

    private static final int[] kingTable = {
        20, 30, 10,  0,  0, 10, 30, 20,
        20, 20,  0,  0,  0,  0, 20, 20,
        -10,-20,-20,-20,-20,-20,-20,-10,
        -20,-30,-30,-40,-40,-30,-30,-20,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30
    };

    private int material(PlayerColor playerColor){
        int materialScore = 0;
        switch (playerColor){
            case WHITE:
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * PAWN;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * KNIGHT;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * BISHOP;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * ROOK;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * QUEEN;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.WHITE) * KING;
                break;
            case BLACK:
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * PAWN;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * KNIGHT;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * BISHOP;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * ROOK;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * QUEEN;
                materialScore += bitBoard.getOccSquaresByColor(PlayerColor.BLACK) * KING;
                break;
        }
        return materialScore;
    }

    private int position(PlayerColor playerColor){
        int positionScore = 0;
        for (int square = 0; square < 64; square++) {
        }
        return positionScore;
    }

    private int popBit(long[] board){
        int index  = Long.numberOfTrailingZeros(board[0]);
        board[0] &= ~(1L << index);
        return index;
    }
    public int getScore(){
        int score = 0;
        return score;
    }
}