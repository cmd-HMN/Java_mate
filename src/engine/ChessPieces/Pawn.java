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
        long dForward = 0L;
        if((move & BitBoard.RANK_2) != 0){
            dForward = wPawn_forward(forward, empty);
        }
        return forward | dForward;
    }
    
    private long bPawn_DForward(long move, long empty){
        long forward = bPawn_forward(move, empty);
        long dForward = 0L;
        if((move & BitBoard.RANK_7) != 0){
            dForward = bPawn_forward(forward, empty);
        }
        return forward | dForward;
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
        return bPawn_capture(move) & white_occ;

    }

    @Override
    public long white_get_possible_pieces(long move, long empty, long black_occ){
        return white_possible_attack(move, black_occ, empty) | white_possible_moves(move, empty);
    }

    @Override
    public long black_get_possible_pieces(long move, long empty ,long white_occ){
        return black_possible_attack(move,white_occ, empty) | black_possible_moves(move, empty);
    }
      // for debugging
      public void printBoardWithMoves(long possibleMoves) {
        if(possibleMoves == 0L){
            System.out.println("No possible moves");
        }
        long fullBoard = 0xFFFFFFFFFFFFFFFFL;
        long mask = 1L;
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < 64; i++) {
            if ((possibleMoves & mask) != 0) {
                int row = 7 - (i / 8);
                int col = i % 8;
                board[row][col] = '*';
            }
            mask <<= 1;
        }

        System.out.println("Board with Possible Moves:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
