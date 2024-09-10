import engine.src.Board.BitBoard;
import engine.src.Board.Moves.Moves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        Moves moves = new Moves(board);

        long initialPawnPosition = 0x0000000000000100L; 
        moves.movePawnOneSquare(initialPawnPosition);

        board.printBoard();
    }
}
