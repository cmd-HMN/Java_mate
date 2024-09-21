package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Bishop extends Pieces {

    @Override
    public long white_possible_moves(long move, long empty) {
        return ClassicMoves.bishop_move(move, empty, false);
    }
    
    @Override
    public long black_possible_moves(long move, long empty) {
        return ClassicMoves.bishop_move(move, empty, false);
    }

    @Override
    public long white_possible_attack(long move, long black_occ, long empty) {
        return ClassicMoves.bishop_move(move, empty, true) & black_occ;
    }

    @Override
    public long black_possible_attack(long move, long white_occ, long empty) {
        return ClassicMoves.bishop_move(move, empty, true) & white_occ;
    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ) {
        return white_possible_attack(move, black_occ, empty) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty, long white_occ) {
        return black_possible_attack(move, white_occ, empty) | black_possible_moves(move, empty);
    }
}