package src.engine.ChessPieces;

import src.engine.Moves.ClassicMoves;

public class Rook {
    
    public long white_possible_moves(long from, long empty) {
        return ClassicMoves.rook_move(from, empty, false) & empty;
    }

    public long black_possible_moves(long from, long empty) {
        return ClassicMoves.rook_move(from, empty, false) & empty;
    }

    public long white_possible_attack(long from, long black_occ, long empty) {
        return ClassicMoves.rook_move(from, black_occ, true) & black_occ;
    }

    public long black_possible_attack(long from, long white_occ, long empty) {
        return ClassicMoves.rook_move(from, empty, true) & white_occ;
    }

    public long white_get_possible_pieces(long from, long empty, long black_occ) {
        return white_possible_attack(from, black_occ, empty) | white_possible_moves(from, empty);
    }

    public long black_get_possible_pieces(long from, long empty, long white_occ) {
        return black_possible_attack(from, white_occ, empty) | black_possible_moves(from, empty);
    }
    public void printPossibleMoves(long possibleMoves) {
        String binaryString = Long.toBinaryString(possibleMoves);
        binaryString = String.format("%64s", binaryString).replace(' ', '0');
        System.out.println("Possible Moves (Binary):");
        System.out.println(binaryString);
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
