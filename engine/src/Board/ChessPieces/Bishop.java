package engine.src.Board.ChessPieces;

import engine.src.Board.BitBoard;
import engine.src.Board.Bits.Bits;

public class Bishop {

    // Method to generate all possible moves for a bishop at a given position
    public static long generateBishopMoves(long square, long occupied) {
        long bishopMoves = 0L; // Stores the moves bitboard
        int[] directions = { 7, 9, -7, -9 }; // Diagonal directions

        for (int direction : directions) {
            long currentSquare = square; // Start from the given square
            while (true) {
                // Move to the next square in the given direction
                currentSquare += direction;

                // Break if out of bounds
                if (currentSquare < 0 || currentSquare >= 64)
                    break;

                // Prevent wrapping around the edges of the board
                if ((direction == 7 || direction == -9) && (currentSquare % 8 == 0))
                    break;
                if ((direction == -7 || direction == 9) && (currentSquare % 8 == 7))
                    break;

                // Calculate the square's bitboard representation
                long currentBit = 1L << currentSquare;

                // Add the current square to the moves bitboard
                bishopMoves |= currentBit;

                // Stop if a piece is blocking the way
                if ((currentBit & occupied) != 0)
                    break;
            }
        }
        return bishopMoves;
    }

    public static void main(String[] args) {
        // Example usage
        long square = Bits.C1; // Bishop is on d4 (square 27)
        long occupied = 0x0000000000280000L; // Example of some pieces blocking the way

        // Generate bishop moves
        long moves = generateBishopMoves(square, occupied);
        
        BitBoard board = new BitBoard();

        board.setBishopMove(moves);
        // Print the bitboard as binary to visualize the moves
        board.printBoard();
    }
}