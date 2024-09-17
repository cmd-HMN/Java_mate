package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Bishop extends Pieces {

    public long possible_move(long move) {
        return (ClassicMoves.bishop_north_east(move) | ClassicMoves.bishop_north_west(move) |
                ClassicMoves.bishop_south_east(move) | ClassicMoves.bishop_south_west(move));
    }

    @Override
    public long white_possible_moves(long move, long empty) {
        return possible_move(move) & empty;
    }

    @Override
    public long black_possible_moves(long move, long empty) {
        return possible_move(move) & empty;
    }

    @Override
    public long white_possible_attack(long move, long black_occ) {
        return possible_move(move) & black_occ;
    }

    @Override
    public long black_possible_attack(long move, long white_occ) {
        return possible_move(move) & white_occ;
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