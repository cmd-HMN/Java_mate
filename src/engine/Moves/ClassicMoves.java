package src.engine.Moves;

import src.engine.BitBoard;

// Moves chess engine 
// Queen, bishop, rook -> MOZI
// king, pawns, knight -> HMN
// adding the feature castling, capture and promotion and checkmate -> HMN


// all the fundamental and classics moves
public class ClassicMoves {

    public static long south(long pos) {
        return pos >>> 8;
    }

    public static long north(long pos) {
        return pos << 8;
    }

    public static long east(long pos) {
        return pos << 1 & BitBoard.FILE_A;
    }

    public static long north_east(long pos) {
        return (pos << 9) & BitBoard.FILE_A;
    }

    public static long south_east(long pos) {
        return (pos >>> 7) & BitBoard.FILE_A;
    }

    public static long west(long pos) {
        return pos >>> 1 & BitBoard.FILE_H;
    }

    public static long north_west(long pos) {
        return (pos << 7) & BitBoard.FILE_H;
    }

    public static long south_west(long pos) {
        return (pos >>> 9) & BitBoard.FILE_H;
    }

    // Knight
    public static long kNorth_NEast(long pos) {
        return (pos << 17) & BitBoard.FILE_A;
    }

    public static long kNorth_EEast(long pos) {
        return (pos << 10) & BitBoard.FILE_AB;
    }

    public static long KNorth_WWest(long pos) {
        return (pos << 6) & BitBoard.FILE_GH;
    }

    public static long KNorth_NWest(long pos) {
        return (pos << 15) & BitBoard.FILE_H;
    }

    public static long kSouth_EEast(long pos) {
        return (pos >> 6) & BitBoard.FILE_AB;
    }

    public static long KSouth_SEast(long pos) {
        return (pos >> 15) & BitBoard.FILE_A;
    }

    public static long KSouth_SWest(long pos) {
        return (pos >> 17) & BitBoard.FILE_H;
    }

    public static long KSouth_WWest(long pos) {
        return (pos >> 10) & BitBoard.FILE_GH;
    }

    public static long bishop_move(long pos, long unOcc, boolean attack) {

        long bishopMoves = 0L;
        long currentSquare = pos;

        // North-East direction
        while ((north_west(currentSquare) & unOcc) != 0) {
            bishopMoves |= north_west(currentSquare);
            currentSquare = north_west(currentSquare);
        }

        // North-West direction
        if(attack){
            bishopMoves |= north_west(currentSquare);
        }

        // South-East direction
        currentSquare = pos;
        while ((south_west(currentSquare) & unOcc) != 0) {
            bishopMoves |= south_west(currentSquare);
            currentSquare = south_west(currentSquare);
        }

        if(attack){
            bishopMoves |= south_west(currentSquare);
        }

        // // South-West direction
        currentSquare = pos;
        while ((south_east(currentSquare) & unOcc) != 0) {
            bishopMoves |= south_east(currentSquare);
            currentSquare = south_east(currentSquare);
        }

        if(attack){
            bishopMoves |= south_east(currentSquare);
        }

        // // North-West direction
        currentSquare = pos;
        while ((north_east(currentSquare) & unOcc) != 0) {
            bishopMoves |= north_east(currentSquare);
            currentSquare = north_east(currentSquare);
        }

        if(attack){
            bishopMoves |= north_east(currentSquare);
        }
        return bishopMoves;
    }

    public static long rook_move(long pos, long unOcc, boolean attack) {
        long rookMoves = 0L;
    
        // North direction
        long currentSquare = pos;
        while ((north(currentSquare) & unOcc) != 0) {
            rookMoves |= north(currentSquare);
            currentSquare = north(currentSquare);
        }
        if (attack) {
            rookMoves |= north(currentSquare);
        }
    
        // South direction
        currentSquare = pos;
        while ((south(currentSquare) & unOcc) != 0) {
            rookMoves |= south(currentSquare);
            currentSquare = south(currentSquare);
        }
        if (attack) {
            rookMoves |= south(currentSquare);
        }
    
        // East direction
        currentSquare = pos;
        while ((east(currentSquare) & unOcc) != 0) {
            rookMoves |= east(currentSquare);
            currentSquare = east(currentSquare);
        }
        if (attack) {
            rookMoves |= east(currentSquare);
        }
    
        // West direction
        currentSquare = pos;
        while ((west(currentSquare) & unOcc) != 0) {
            rookMoves |= west(currentSquare);
            currentSquare = west(currentSquare);
        }
        if (attack) {
            rookMoves |= west(currentSquare);
        }
    
        return rookMoves;
    }

    

    public void printPossibleMoves(long possibleMoves) {
        String binaryString = Long.toBinaryString(possibleMoves);
        binaryString = String.format("%64s", binaryString).replace(' ', '0');
        System.out.println("Possible Moves (Binary):");
        System.out.println(binaryString);
    }

    // for debugging
    public static void printBoardWithMoves(long possibleMoves) {
        if(possibleMoves == 0L){
            System.out.println("No possible moves");
        }
        long fullBoard = 0xFFFFFFFFFFFFFFFFL;
        long mask = 1L;
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < 64; i++) {
            if ((possibleMoves & mask) != 0) {
                int row = 7 - (i / 8);
                int col = i % 8;
                board[row][col] = '*';
            }
            mask <<= 1;
        }

        System.out.println("Board with Possible Moves:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}