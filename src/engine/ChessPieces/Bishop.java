package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;
import src.engine.BitBoard;

public class Bishop extends Pieces {

    private long generateBishopMoves(long position, long occupied) {
        long bishopMoves = 0L;
        long currentSquare = position;

        // North-East direction
        while (true) {
            currentSquare = ClassicMoves.bishop_north_east(currentSquare);
            if ((currentSquare & BitBoard.FILE_A) != 0)
                break; // Edge of the board
            bishopMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // North-West direction
        while (true) {
            currentSquare = ClassicMoves.bishop_north_west(currentSquare);
            if ((currentSquare & BitBoard.FILE_H) != 0)
                break; // Edge of the board
            bishopMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // South-East direction
        while (true) {
            currentSquare = ClassicMoves.bishop_south_east(currentSquare);
            if ((currentSquare & BitBoard.FILE_A) != 0)
                break; // Edge of the board
            bishopMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        currentSquare = position; // Reset position

        // South-West direction
        while (true) {
            currentSquare = ClassicMoves.bishop_south_west(currentSquare);
            if ((currentSquare & BitBoard.FILE_H) != 0)
                break; // Edge of the board
            bishopMoves |= currentSquare;
            if ((currentSquare & occupied) != 0)
                break; // Blocked by own piece
        }

        return bishopMoves;
    }

    @Override
    public long white_possible_moves(long move, long empty) {
        return generateBishopMoves(move, empty);
    }

    @Override
    public long black_possible_moves(long move, long empty) {
        return generateBishopMoves(move, empty);
    }

    @Override
    public long white_possible_attack(long move, long black_occ) {
        return generateBishopMoves(move, black_occ);
    }

    @Override
    public long black_possible_attack(long move, long white_occ) {
        return generateBishopMoves(move, white_occ);
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
