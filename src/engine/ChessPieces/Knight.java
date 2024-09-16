package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Knight extends Pieces{
    
    public long possible_move(long move){
        return (ClassicMoves.kNorth_NEast(move) | ClassicMoves.kNorth_EEast(move) |
        ClassicMoves.KNorth_WWest(move) | ClassicMoves.KNorth_NWest(move) |
        ClassicMoves.kSouth_EEast(move) | ClassicMoves.KSouth_SEast(move) |
        ClassicMoves.KSouth_SWest(move) | ClassicMoves.KSouth_WWest(move));
    }

    @Override
    public long white_possible_moves(long move, long empty){
        return possible_move(move) & empty;
    }
    
    @Override
    public long black_possible_moves(long move, long empty){
        return possible_move(move) & empty;
    }
    
    @Override
    public long white_possible_attack(long move, long black_occ){
        return (possible_move(move)) & black_occ;
    }
    
    @Override
    public long black_possible_attack(long move, long white_occ){
        return (possible_move(move)) & white_occ;
    }
    
    @Override
    public long white_get_possible_pieces(long move, long white_occ, long empty){
        return white_possible_attack(move, white_occ) | white_possible_moves(move, empty);
    }
    
    @Override
    public long black_get_possible_pieces(long move, long black_occ, long empty){
        return black_possible_attack(move, black_occ) | black_possible_moves(move, empty);
    }
}
