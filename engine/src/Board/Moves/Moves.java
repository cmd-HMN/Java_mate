package engine.src.Board.Moves;

import engine.src.Board.BitBoard;
import engine.src.Board.ChessPieces.King;
import engine.src.Board.ChessPieces.Knight;
import engine.src.Board.ChessPieces.Pawn;

// Moves chess engine 
// Queen, bishop, rook -> MOZI
// king, pawns, knight -> HMN

public  class Moves {
    private BitBoard bitBoard;
    Pawn pawn = new Pawn();
    King king = new King();
    Knight knight = new Knight();

    public Moves(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }

    public long KnightMove(long from, long to, long occ, long whiteKnights){

        long possible_moves = knight.white_possible_moves(from, BitBoard.empty);
        
        System.out.println("moves " + possible_moves);

        if((to & possible_moves) != 0){
            whiteKnights &= ~ from;
            whiteKnights |= to;
            System.out.println("Worked");
            return whiteKnights;
        }

        System.out.println("Failed");
        return whiteKnights;
    }

    public long PawnMove(long from , long to, long occ, long wPawn){
        long possible_moves = pawn.white_possible_moves(from, BitBoard.empty);
  
        if((to & possible_moves) != 0){
            wPawn &= ~ from;
            wPawn |= to;
            System.out.println("Worked");
            return wPawn;
        }
        
        System.out.println("Failed");
        return wPawn;
    }
    
    public long KingMove(long from, long to, long occ, long wPawn){
        long possible_moves = king.white_possible_moves(from, BitBoard.empty);

        long king = bitBoard.wGetKing();

        if((king << from) != 0){
            if((to & possible_moves) != 0){
                wPawn &= ~ from;
                wPawn |= to;
                System.out.println("Worked");
                return wPawn;
            }
        }

        System.out.println("Failed");
        return wPawn;

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


    //Knight
    public static long kNorth_NEast(long pos){
        return (pos << 17) & BitBoard.FILE_A;
    }

    public static long kNorth_EEast(long pos){
        return (pos << 10) & BitBoard.FILE_AB;
    }

    
    public static long KNorth_WWest(long pos){
        return (pos << 6) & BitBoard.FILE_GH;
    }

    public static long KNorth_NWest(long pos){
        return (pos << 15) & BitBoard.FILE_H;
    }

    public static long kSouth_EEast(long pos){
        return (pos >> 6) & BitBoard.FILE_AB;
    }

    public static long KSouth_SEast(long pos){
        return (pos >> 15) & BitBoard.FILE_A;
    }
    
    public static long KSouth_SWest(long pos){
        return (pos >> 17) & BitBoard.FILE_H;
    }

    public static long KSouth_WWest(long pos){
        return (pos >> 10) & BitBoard.FILE_GH;
    }

}
