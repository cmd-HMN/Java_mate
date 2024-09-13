package engine.src.Board.Moves;

import engine.src.Board.BitBoard;
import engine.src.Board.ChessPieces.Pawn;

// Moves chess engine 
// Queen, bishop, rook -> MOZI
// king, pawns, knight -> HMN

public  class Moves {
    private BitBoard bitBoard;
    Pawn pawn = new Pawn();

    public Moves(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }

    public long PawnMove(long from , long to, long occ, long wPawn){
        long possible_moves = pawn.white_possible_moves(from, BitBoard.empty);

        System.out.println("moves" + possible_moves);
        if((to & possible_moves) != 0){
            wPawn &= ~ from;
            wPawn |= to;
            System.out.println("Worked");
            return wPawn;
        }
        
        System.out.println("Failed");
        return wPawn;
    }
    
    private static boolean isValidMove(long position, long move) {
        return (move & BitBoard.FILE_A) == 0;
    }

    public void movePawnOneSquare(long position) {
        long newPosition = north(position);

        if (isValidMove(position, newPosition)) {
            long whitePawns = bitBoard.getWhitePawns();
            bitBoard.setWhitePawns((whitePawns & ~position) | newPosition);
        }
    }
    public static long south(long pos){
        return pos >> 8;
    }
    public static long north(long pos){
        return pos << 8;
    }

    public static long east(long pos){
        return pos << 1 & BitBoard.FILE_A;
    }
    
    public static long north_east(long pos){
        return (pos << 9) & BitBoard.FILE_A;
    }
    
    public static long south_east(long pos){
        return (pos >> 7) & BitBoard.FILE_A;
    }
    
    public static long west(long pos){
        return pos >> 1 & BitBoard.FILE_H;
    }
    
    public static long north_west(long pos){
        return (pos << 7) & BitBoard.FILE_H;
    }
    
    public static long south_west(long pos){
        return (pos >> 9) & BitBoard.FILE_H;
    }
}
