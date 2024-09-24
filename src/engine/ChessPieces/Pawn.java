package src.engine.ChessPieces;

import src.engine.BitBoard;
import src.engine.Moves.ClassicMoves;

public class Pawn extends Pieces{

    private long wPawn_forward(long move, long empty){
        return ClassicMoves.north(move) & empty;
    };

    private long bPawn_forward(long move, long empty){
        return ClassicMoves.south(move) & empty;
    }

    private long wPawn_DForward(long move, long empty){
        long forward = wPawn_forward(move, empty);
        return ClassicMoves.north(forward) & empty & ~BitBoard.RANK_2;
    }

    private long bPawn_DForward(long move, long empty){
        long forward = bPawn_forward(move, empty);
        return ClassicMoves.south(forward) & empty & ~BitBoard.RANK_7;
    }

    private long wPawn_capture(long move){
        return ClassicMoves.north_east(move) | ClassicMoves.north_west(move);
    }

    private long bPawn_capture(long move){
        return ClassicMoves.south_east(move) | ClassicMoves.south_west(move);
    }

    @Override
    public long white_possible_moves(long move, long empty){
        return wPawn_forward(move, empty) | wPawn_DForward(move, empty);
    }

    @Override
    public long black_possible_moves(long from, long empty){
        return bPawn_forward(from, empty) | bPawn_DForward(from, empty);
    }

    @Override
    public long white_possible_attack(long move, long black_occ, long empty){
        return wPawn_capture(move) & black_occ;
    }

    @Override
    public long black_possible_attack(long move, long white_occ, long empty){
        return bPawn_capture(move) & ~white_occ;

    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, black_occ, empty) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move,white_occ, empty) | black_possible_moves(move, empty);
    }

}
