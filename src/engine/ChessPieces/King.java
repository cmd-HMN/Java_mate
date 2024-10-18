package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class King extends Pieces{

    @Override
    public long white_possible_moves(long move, long empty){
        long diagonal_move = (ClassicMoves.north_east(move) | ClassicMoves.north_west(move) | ClassicMoves.south_east(move) | ClassicMoves.south_west(move) )& empty;
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & empty | diagonal_move;
    };
    
    @Override
    public long black_possible_moves(long move, long empty){
        long diagonal_move = (ClassicMoves.north_east(move) | ClassicMoves.north_west(move) | ClassicMoves.south_east(move) | ClassicMoves.south_west(move) )& empty;
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & empty | diagonal_move;
    };
    
    @Override
    public long white_possible_attack(long move, long black_occ, long empty){
        long diagonal_move = (ClassicMoves.north_east(move) | ClassicMoves.north_west(move) | ClassicMoves.south_east(move) | ClassicMoves.south_west(move) )& black_occ;
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move)) & black_occ | diagonal_move;
    }
    
    @Override
    public long black_possible_attack(long move, long white_occ, long empty){
        long diagonal_move = (ClassicMoves.north_east(move) | ClassicMoves.north_west(move) | ClassicMoves.south_east(move) | ClassicMoves.south_west(move) ) & white_occ;
        return (ClassicMoves.north(move) | ClassicMoves.south(move) | ClassicMoves.east(move) | ClassicMoves.west(move) | diagonal_move) & white_occ;
    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, black_occ, empty) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move, white_occ, empty) | black_possible_moves(move, empty);
    }
}
