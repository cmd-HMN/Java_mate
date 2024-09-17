package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;
import src.engine.BitBoard;

public class Rook extends Pieces {

    private long generateRookMoves(long position, long occupied) {
        long rookMoves = 0L;
        long currentSquare = position;

        // North direction
        while (true) {
            currentSquare = ClassicMoves.rook_north(currentSquare);
            if ((currentSquare & BitBoard.FILE_A) != 0)
                break; // Edge of the board
            rookMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // South direction
        while (true) {
            currentSquare = ClassicMoves.rook_south(currentSquare);
            if ((currentSquare & BitBoard.FILE_A) != 0)
                break; // Edge of the board
            rookMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // East direction
        while (true) {
            currentSquare = ClassicMoves.rook_east(currentSquare);
            if ((currentSquare & BitBoard.FILE_H) != 0)
                break; // Edge of the board
            rookMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // West direction
        while (true) {
            currentSquare = ClassicMoves.rook_west(currentSquare);
            if ((currentSquare & BitBoard.FILE_H) != 0)
                break; // Edge of the board
            rookMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        return rookMoves;
    }

    @Override
    public long white_possible_moves(long move, long empty) {
        return generateRookMoves(move, empty);
    }

    @Override
    public long black_possible_moves(long move, long empty) {
        return generateRookMoves(move, empty);
    }

    @Override
    public long white_possible_attack(long move, long black_occ) {
        return generateRookMoves(move, black_occ);
    }

    @Override
    public long black_possible_attack(long move, long white_occ) {
        return generateRookMoves(move, white_occ);
    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ) {
        return white_possible_attack(move, black_occ) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty, long white_occ) {
        return black_possible_attack(move, white_occ) | black_possible_moves(move, empty);
    }
}
