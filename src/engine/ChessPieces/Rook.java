package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Rook {
    
    public long white_possible_moves(long from, long empty) {
        return ClassicMoves.rook_move(from, empty, false) & empty;
    }

    public long black_possible_moves(long from, long empty) {
        return ClassicMoves.rook_move(from, empty, false) & empty;
    }

    public long white_possible_attack(long from, long black_occ, long empty) {
        return ClassicMoves.rook_move(from, black_occ, true) & black_occ;
    }

    public long black_possible_attack(long from, long white_occ, long empty) {
        return ClassicMoves.rook_move(from, empty, true) & white_occ;
    }

    public long white_get_possible_pieces(long from, long empty, long black_occ) {
        return white_possible_attack(from, black_occ, empty) | white_possible_moves(from, empty);
    }

    public long black_get_possible_pieces(long from, long empty, long white_occ) {
        return black_possible_attack(from, white_occ, empty) | black_possible_moves(from, empty);
    }
}
