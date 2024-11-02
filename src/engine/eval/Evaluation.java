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
        0, 0, 0, 0, 0, 0, 0, 0, 
        50, 50, 50, 50, 50, 50, 50, 50, 
        10, 10, 20, 30, 30, 20, 10, 10, 
        5, 5, 10, 25, 25, 10, 5, 5, 
        0, 0, 0, 20, 20, 0, 0, 0, 
        5, -5, -10, 0, 0, -10, -5, 5, 
        5, 10, 10, -20, -20, 10, 10, 5, 
        0, 0, 0, 0, 0, 0, 0, 0
    };

    private static final int[] knightTable = {
        -50, -40, -30, -30, -30, -30, -40, -50, 
        -40, -20, 0, 0, 0, 0, -20, -40, 
        -30, 0, 10, 15, 15, 10, 0, -30, 
        -30, 5, 15, 20, 20, 15, 5, -30, 
        -30, 0, 15, 20, 20, 15, 0, -30, 
        -30, 5, 10, 15, 15, 10, 5, -30, 
        -40, -20, 0, 5, 5, 0, -20, -40, 
        -50, -40, -30, -30, -30, -30, -40, -50};

    private static final int[] bishopTable =  {
        -20, -10, -10, -10, -10, -10, -10, -20, 
        -10, 0, 0, 0, 0, 0, 0, -10, 
        -10, 0, 5, 10, 10, 5, 0, -10, 
        -10, 5, 5, 10, 10, 5, 5, -10, 
        -10, 0, 10, 10, 10, 10, 0, -10, 
        -10, 10, 10, 10, 10, 10, 10, -10, 
        -10, 5, 0, 0, 0, 0, 5, -10, 
        -20, -10, -10, -10, -10, -10, -10, -20};

    private static final int[] rookTable = {0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, -5, 0, 0,
        0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5,
        -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 5, 5, 0, 0, 0};
  

    private static final int[] queenTable = {-20, -10, -10, -5, -5, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0,
        -10, -10, 0, 5, 5, 5, 5, 0, -10, -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, 5, 5, 5, 5, 0, -5, -10, 5, 5,
        5, 5, 5, 0, -10, -10, 0, 5, 0, 0, 0, 0, -10, -20, -10, -10, -5, -5, -10, -10, -20};

    private static final int[] kingMidTable = {-30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50,
        -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40,
        -30, -20, -30, -30, -40, -40, -30, -30, -20, -10, -20, -20, -20, -20, -20, -20, -10, 20, 20,
        0, 0, 0, 0, 20, 20, 20, 30, 10, 0, 0, 10, 30, 20};
        
    private static final int kingEndTable[] = {-50, -40, -30, -20, -20, -30, -40, -50, -30, -20, -10, 0,
        0, -10, -20, -30, -30, -10, 20, 30, 30, 20, -10, -30, -30, -10, 30, 40, 40, 30, -10, -30, -30,
        -10, 30, 40, 40, 30, -10, -30, -30, -10, 20, 30, 30, 20, -10, -30, -30, -30, 0, 0, 0, 0, -30,
        -30, -50, -30, -30, -30, -30, -30, -30, -50};
      

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
                    positionScore += pawnTable[index];
                    pawnScore += pawnTable[index];
                    pawn &= ~1L << index;
                }   
                long knight = bitBoard.whiteKnights;
                while(knight != 0){
                    int index = popBit(new long[]{knight});
                    positionScore += knightTable[index];
                    knightScore += knightTable[index];
                    knight &= ~1L << index;
                }
                long bishop = bitBoard.whiteBishops;
                while(bishop != 0){
                    int index = popBit(new long[]{bishop});
                    positionScore += bishopTable[index];
                    bishopScore += bishopTable[index];
                    bishop &= ~1L << index;
                }
                long rook = bitBoard.whiteRooks;
                while(rook != 0){
                    int index = popBit(new long[]{rook});
                    positionScore += rookTable[index];
                    rookScore += rookTable[index];
                    rook &= ~1L << index;
                }
                long queen = bitBoard.whiteQueens;
                while(queen != 0){
                    int index = popBit(new long[]{queen});
                    positionScore += queenTable[index];
                    queenScore += queenTable[index];
                    queen &= ~1L << index;
                }
                long king = bitBoard.whiteKings;
                while(king != 0){
                    int index = popBit(new long[]{king});
                    positionScore += Math.floor(index / 100) <= 13 ? kingMidTable[index] : kingEndTable[index]; 
                    kingScore += Math.floor(index / 100) <= 13 ? kingMidTable[index] : kingEndTable[index];
                    king &= ~1L << index;
                }
                return positionScore;
            case BLACK:
                long bPawn = bitBoard.blackPawns;
                while(bPawn != 0){
                    int index = popBit(new long[]{bPawn});
                    positionScore += pawnTable[index];
                    pawnScore += pawnTable[index];
                    bPawn &= ~1L << index;
                }
                long bKnight = bitBoard.blackKnights;
                while(bKnight != 0){
                    int index = popBit(new long[]{bKnight});
                    positionScore += knightTable[index];
                    knightScore += knightTable[index];
                    bKnight &= ~1L << index;
                }
                long bBishop = bitBoard.blackBishops;
                while(bBishop != 0){
                    int index = popBit(new long[]{bBishop});
                    positionScore += bishopTable[index];
                    bishopScore += bishopTable[index];
                    bBishop &= ~1L << index;
                }
                long bRook = bitBoard.blackRooks;
                while(bRook != 0){
                    int index = popBit(new long[]{bRook});
                    positionScore += rookTable[index];
                    rookScore += rookTable[index];
                    bRook &= ~1L << index;
                }
                long bQueen = bitBoard.blackQueens;
                while(bQueen != 0){
                    int index = popBit(new long[]{bQueen});
                    positionScore += queenTable[index];
                    queenScore += queenTable[index];
                    bQueen &= ~1L << index;
                }
                long bKing = bitBoard.blackKings;
                while(bKing != 0){
                    int index = popBit(new long[]{bKing});
                    positionScore += Math.floor(index / 100) <= 13 ? kingMidTable[index] : kingEndTable[index];
                    kingScore += Math.floor(index / 100) <= 13 ? kingMidTable[index] : kingEndTable[index];
                    bKing &= ~1L << index;
                }
                System.out.println("Black");
                System.out.println("Pawn Score");
                System.out.println(pawnScore);
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

        System.out.println("White Material");
        System.out.println(material(PlayerColor.WHITE));
        System.out.println("Black Material");
        System.out.println(material(PlayerColor.BLACK));

        System.out.println("White Position");
        System.out.println(position(PlayerColor.WHITE));
        System.out.println("Black Position");
        System.out.println(position(PlayerColor.BLACK));
        System.out.println("SCORE");
        System.out.println(score);
        return score;
    }
}