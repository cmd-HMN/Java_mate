package src.engine.Moves;

import src.engine.BitBoard;

// Moves chess engine 
// Queen, bishop, rook -> MOZI
// king, pawns, knight -> HMN
// adding the feature castling, capture and promotion and checkmate -> HMN

public class ClassicMoves {

    public static long south(long pos) {
        return pos >> 8;
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
        return (pos >> 7) & BitBoard.FILE_A;
    }

    public static long west(long pos) {
        return pos >> 1 & BitBoard.FILE_H;
    }

    public static long north_west(long pos) {
        return (pos << 7) & BitBoard.FILE_H;
    }

    public static long south_west(long pos) {
        return (pos >> 9) & BitBoard.FILE_H;
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

    // Bishop
    public static long bishop_north_east(long pos) {
        return (pos << 9) & BitBoard.FILE_A;
    }

    public static long bishop_north_west(long pos) {
        return (pos << 7) & BitBoard.FILE_H;
    }

    public static long bishop_south_east(long pos) {
        return (pos >> 7) & BitBoard.FILE_A;
    }

    public static long bishop_south_west(long pos) {
        return (pos >> 9) & BitBoard.FILE_H;
    }
}