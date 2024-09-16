package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class King extends Pieces{

    @Override
    public long white_possible_moves(long move, long empty){
       return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & empty;
    };

    @Override
    public long black_possible_moves(long move, long empty){
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & empty;
    };

    @Override
    public long white_possible_attack(long move, long black_occ){
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & black_occ;
    }

    @Override
    public long black_possible_attack(long move, long white_occ){
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & white_occ;
    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, black_occ) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move, white_occ) | black_possible_moves(move, empty);
    }
}
