package engine.src.Board.ChessPieces;

import engine.src.Board.Moves.Moves;

public class Pawn{

    private long white_single_forward(long move, long empty){
        return Moves.north(move) & empty;
    };

    private long black_single_forward(long move, long empty){
        return Moves.north(move) & empty;
    }

    private long white_double_forward(long move, long empty){
        return Moves.north(move);
    }

}
