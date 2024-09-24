package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Queen {

    public long white_possible_moves(long from, long empty) {
        return ClassicMoves.bishop_move(from, empty, false) | ClassicMoves.rook_move(from, empty, false);
    }

    public long black_possible_moves(long from, long empty) {
        return ClassicMoves.bishop_move(from, empty, false) | ClassicMoves.rook_move(from, empty, false);
    }

    public long white_possible_attack(long from, long black_occ, long empty) {
        long move = (ClassicMoves.bishop_move(from, empty, true) | ClassicMoves.rook_move(from, empty, true));
        return move & black_occ;
    }
    
    public long black_possible_attack(long from, long white_occ, long empty) {
        long move = (ClassicMoves.bishop_move(from, empty, true) | ClassicMoves.rook_move(from, empty, true));
        return move & white_occ;
    }

    public long white_get_possible_pieces(long from, long empty, long black_occ) {
        return white_possible_attack(from, black_occ, empty) | white_possible_moves(from, empty);
    }

    public long black_get_possible_pieces(long from, long empty, long white_occ) {
        return black_possible_attack(from, white_occ, empty) | black_possible_moves(from, empty);
    }
  
}
