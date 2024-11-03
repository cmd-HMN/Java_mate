package src.engine.eval;

import src.engine.BitBoard;
import src.engine.Type.PlayerColor;

public class Evaluation {
    int PAWN = 1;
    int KNIGHT = 3;
    int BISHOP = 3;
    int ROOK = 5;
    int QUEEN = 9;
    int KING = 10;
    BitBoard bitBoard;

    public Evaluation(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }
    
    private static final int[] whitePawnTable = {
        0,  0,  0,  0,  0,  0,  0,  0,
        90, 90, 90, 90, 90, 90, 90, 90,
        30, 30, 40, 60, 60, 40, 30, 30,
        10, 10, 20, 40, 40, 20, 10, 10,
        5,  5, 10, 20, 20, 10,  5,  5,
        0,  0,  0,-10,-10,  0,  0,  0,
        5, -5,-10,  0,  0,-10, -5,  5,
        0,  0,  0,  0,  0,  0,  0,  0
    };

    private static final int[] blackPawnTable = {
        0,  0,  0,  0,  0,  0,  0,  0,
        5, -5,-10,  0,  0,-10, -5,  5,
        0,  0,  0,-10,-10,  0,  0,  0,
        5,  5, 10, 20, 20, 10,  5,  5,
        10, 10, 20, 40, 40, 20, 10, 10,
        30, 30, 40, 60, 60, 40, 30, 30,
        90, 90, 90, 90, 90, 90, 90, 90,
        0,  0,  0,  0,  0,  0,  0,  0
    };

    private static final int[] whiteKnightTable =  {
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50
    };

    private static final int[] blackKnightTable = {
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50,
    };


    private static final int[] whiteBishopTable = {
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0, 10, 15, 15, 10,  0,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    };

    private static final int[] blackBishopTable = {
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  0, 10, 15, 15, 10,  0,-10,
        -10,  5, 10, 15, 15, 10,  5,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    };

    private static final int[] whiteRookTable = {
        0,  0,  0,  0,  0,  0,  0,  0,
        5, 20, 20, 20, 20, 20, 20,  5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        0,  0,  0,  5,  5,  0,  0,  0
    };

    private static final int[] blackRookTable = {
        0,  0,  0,  5,  5,  0,  0,  0,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        -5,  0,  0,  0,  0,  0,  0, -5,
        5, 20, 20, 20, 20, 20, 20,  5,
        0,  0,  0,  0,  0,  0,  0,  0,
};
    private static final int[] whiteQueenTable = {
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5,  5,  5,  5,  0,-10,
        -5,  0,  5, 10, 10,  5,  0, -5,
        -5,  0,  5, 10, 10,  5,  0, -5,
        -10,  0,  5,  5,  5,  5,  0,-10,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    };

    private static final int[] blackQueenTable =  {
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -10,  5,  5,  5,  5,  5,  0,-10,
        -5,  0,  5, 10, 10,  5,  0, -5,
        -5,  0,  5, 10, 10,  5,  0, -5,
        -10,  0,  5,  5,  5,  5,  0,-10,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    };

    private static final int[] whiteKingTable = {
        -50,-30,-30,-30,-30,-30,-30,-50,
        -30,-30,  0,  0,  0,  0,-30,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-20,-10,  0,  0,-10,-20,-30,
        -50,-40,-30,-20,-20,-30,-40,-50
};
        
    private static final int blackKingTable[] = {
        -50,-40,-30,-20,-20,-30,-40,-50,
        -30,-20,-10,  0,  0,-10,-20,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-30,  0,  0,  0,  0,-30,-30,
        -50,-30,-30,-30,-30,-30,-30,-50
};
      

    private int countPiece(long board){
        int count = 0;
        while(board != 0){
            count++;
            board &= board - 1;
        }
        return count;
    }
    private int material(PlayerColor playerColor){
        int materialScore = 0;
        switch (playerColor){
            case WHITE:
                materialScore = countPiece(bitBoard.whitePawns) * PAWN;
                materialScore += countPiece(bitBoard.whiteKnights) * KNIGHT;
                materialScore += countPiece(bitBoard.whiteBishops) * BISHOP;
                materialScore += countPiece(bitBoard.whiteRooks) * ROOK;
                materialScore += countPiece(bitBoard.whiteQueens) * QUEEN;
                materialScore += countPiece(bitBoard.whiteKings) * KING;
                return materialScore;
            case BLACK:
                materialScore = countPiece(bitBoard.blackPawns) * PAWN;
                materialScore += countPiece(bitBoard.blackKnights) * KNIGHT;
                materialScore += countPiece(bitBoard.blackBishops) * BISHOP;
                materialScore += countPiece(bitBoard.blackRooks) * ROOK;
                materialScore += countPiece(bitBoard.blackQueens) * QUEEN;
                materialScore += countPiece(bitBoard.blackKings) * KING;
                return materialScore;
            default:
                return materialScore;
        }
    }

    private int position(PlayerColor playerColor){
        int positionScore = 0;
        int pawnScore = 0;
        int knightScore = 0;
        int bishopScore = 0;
        int rookScore = 0;
        int queenScore = 0;
        int kingScore = 0;
        switch (playerColor){
            case WHITE:
                long pawn = bitBoard.whitePawns;
                while(pawn != 0){
                    int index = popBit(new long[]{pawn});
                    positionScore += whitePawnTable[index];
                    pawnScore += whitePawnTable[index];
                    pawn &= ~1L << index;
                }   
                long knight = bitBoard.whiteKnights;
                while(knight != 0){
                    int index = popBit(new long[]{knight});
                    positionScore += whiteKnightTable[index];
                    knightScore += whiteKnightTable[index];
                    knight &= ~1L << index;
                }
                long bishop = bitBoard.whiteBishops;
                while(bishop != 0){
                    int index = popBit(new long[]{bishop});
                    positionScore += whiteBishopTable[index];
                    bishopScore += whiteBishopTable[index];
                    bishop &= ~1L << index;
                }
                long rook = bitBoard.whiteRooks;
                while(rook != 0){
                    int index = popBit(new long[]{rook});
                    positionScore += whiteRookTable[index];
                    rookScore += whiteRookTable[index];
                    rook &= ~1L << index;
                }
                long queen = bitBoard.whiteQueens;
                while(queen != 0){
                    int index = popBit(new long[]{queen});
                    positionScore += whiteQueenTable[index];
                    queenScore += whiteQueenTable[index];
                    queen &= ~1L << index;
                }
                long king = bitBoard.whiteKings;
                while(king != 0){
                    int index = popBit(new long[]{king});
                    positionScore += whiteKingTable[index];
                    kingScore += whiteKingTable[index];
                    king &= ~1L << index;
                }
                return positionScore;
            case BLACK:
                long bPawn = bitBoard.blackPawns;
                while(bPawn != 0){
                    int index = popBit(new long[]{bPawn});
                    positionScore += blackPawnTable[index];
                    pawnScore += blackPawnTable[index];
                    bPawn &= ~1L << index;
                }
                long bKnight = bitBoard.blackKnights;
                while(bKnight != 0){
                    int index = popBit(new long[]{bKnight});
                    positionScore += blackKnightTable[index];
                    knightScore += blackKnightTable[index];
                    bKnight &= ~1L << index;
                }
                long bBishop = bitBoard.blackBishops;
                while(bBishop != 0){
                    int index = popBit(new long[]{bBishop});
                    positionScore += blackBishopTable[index];
                    bishopScore += blackBishopTable[index];
                    bBishop &= ~1L << index;
                }
                long bRook = bitBoard.blackRooks;
                while(bRook != 0){
                    int index = popBit(new long[]{bRook});
                    positionScore += blackRookTable[index];
                    rookScore += blackRookTable[index];
                    bRook &= ~1L << index;
                }
                long bQueen = bitBoard.blackQueens;
                while(bQueen != 0){
                    int index = popBit(new long[]{bQueen});
                    positionScore += blackQueenTable[index];
                    queenScore += blackQueenTable[index];
                    bQueen &= ~1L << index;
                }
                long bKing = bitBoard.blackKings;
                while(bKing != 0){
                    int index = popBit(new long[]{bKing});
                    positionScore += blackKingTable[index];
                    kingScore += blackKingTable[index];
                    bKing &= ~1L << index;
                }
                return positionScore;
            default:
                return positionScore;

        }
    }

    private int popBit(long[] board){
        int index  = Long.numberOfTrailingZeros(board[0]);
        board[0] &= ~(1L << index);
        return index;
    }
    public int getScore(){
        int score = 0;
        score += material(PlayerColor.WHITE);
        score += position(PlayerColor.WHITE);
        
        score -= material(PlayerColor.BLACK);
        score -= position(PlayerColor.BLACK);
        System.out.println("SCORE");
        System.out.println(score);
        return score;
    }
}