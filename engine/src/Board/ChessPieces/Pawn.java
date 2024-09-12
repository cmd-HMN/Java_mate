package engine.src.Board.ChessPieces;

import engine.src.Board.Moves.Moves;

//all the pawn moves wil be here
public class Pawn{

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

    public long white_get_possible_piece(long move, long empty){

        long single_forward = wPawn_forward(move, empty);

        long double_forward = wPawn_DForward(move, empty);

        return single_forward | double_forward ;

    }

    public long black_get_possible_piece(long move, long empty){

        long single_forward = bPawn_forward(move, empty);

        long double_forward = bPawn_DForward(move, empty);

        return single_forward | double_forward;

    }

    public long white_possible_attack(long move){
        return wPawn_capture(move);
    }

    public long black_possible_attack(long move){
        return bPawn_capture(move);
    }

    public long movePawn(long from, long to, long empty, long whitePawns){

        long possible_moves = white_get_possible_piece(from, empty);


        if((possible_moves & to) != 0){
            whitePawns &= ~from;
            whitePawns |= to;
            System.out.println(whitePawns);

            System.out.println("Move successful");
            return whitePawns;
        }
        else{
            System.out.println("Move failed");
            return -1;
        }
    }
}
