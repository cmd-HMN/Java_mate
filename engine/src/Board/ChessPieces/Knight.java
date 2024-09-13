package engine.src.Board.ChessPieces;

import engine.src.Board.Moves.Moves;

public class Knight extends Pieces{
    
    public long possible_move(long move){
        return (Moves.kNorth_NEast(move) | Moves.kNorth_EEast(move) |
        Moves.KNorth_WWest(move) | Moves.KNorth_NWest(move) |
        Moves.kSouth_EEast(move) | Moves.KSouth_SEast(move) |
        Moves.KSouth_SWest(move) | Moves.KSouth_WWest(move));
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
    public long white_possible_attack(long move, long black_occ, long empty){
        return (possible_move(move)) & black_occ & ~empty;
    }
    
    @Override
    public long black_possible_attack(long move, long white_occ, long empty){
        return (possible_move(move)) & white_occ & ~empty;
    }
    
    @Override
    public long white_get_possible_pieces(long move, long white_occ, long empty){
        return white_possible_attack(move, white_occ, empty) | white_possible_moves(move, empty);
    }
    
    @Override
    public long black_get_possible_pieces(long move, long black_occ, long empty){
        return black_possible_attack(move, black_occ, empty) | black_possible_moves(move, empty);
    }
}
