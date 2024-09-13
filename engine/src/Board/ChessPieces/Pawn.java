package engine.src.Board.ChessPieces;

import engine.src.Board.Moves.Moves;

//all the pawn moves wil be here
public class Pawn extends Pieces{

    private long wPawn_forward(long move, long empty){
        return Moves.north(move) & empty;
    };

    private long bPawn_forward(long move, long empty){
        return Moves.north(move) & empty;
    }

    private long wPawn_DForward(long move, long empty){
        long forward = wPawn_forward(move, empty);
        return Moves.north(forward) & empty;
    }

    private long bPawn_DForward(long move, long empty){
        long forward = bPawn_forward(move, empty);
        return Moves.north(forward) & empty;
    }

    private long wPawn_capture(long move){
        return Moves.north_east(move) | Moves.north_west(move);
    }

    private long bPawn_capture(long move){
        return Moves.south_east(move) | Moves.south_west(move);
    }

    
    public long white_possible_attack(long move, long black_occ){
        return wPawn_capture(move) & black_occ;
    }

    public long black_possible_attack(long move, long white_occ){
        return bPawn_capture(move) & white_occ;
    }

    public long white_possible_moves(long move, long empty){
        return wPawn_forward(move, empty) | wPawn_DForward(move, empty);
    }

    public long black_possible_moves(long from, long empty){
        return bPawn_forward(from, empty) | bPawn_DForward(from, empty);
    }
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, black_occ) | white_possible_moves(move, empty);
    }
    
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move, white_occ) | black_possible_moves(move, empty);
    }

}
