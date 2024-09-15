package src.engine.ChessPieces;

import src.engine.Moves.Moves;

public class King extends Pieces{

    @Override
    public long white_possible_moves(long move, long empty){
       return (Moves.north(move) | Moves.south(move) | Moves.east(move) | Moves.west(move)) & empty;
    };

    @Override
    public long black_possible_moves(long move, long empty){
        return (Moves.north(move) | Moves.south(move) | Moves.east(move) | Moves.west(move)) & empty;
    };

    @Override
    public long white_possible_attack(long move, long empty, long black_occ){
        return (Moves.north(move) | Moves.south(move) | Moves.east(move) | Moves.west(move)) & black_occ & ~empty;
    }

    @Override
    public long black_possible_attack(long move, long empty, long white_occ){
        return (Moves.north(move) | Moves.south(move) | Moves.east(move) | Moves.west(move)) & white_occ & ~empty;
    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, empty, black_occ) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move, empty, white_occ) | black_possible_moves(move, empty);
    }
}
