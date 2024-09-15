import src.engine.BitBoard;
import src.engine.Bits.Bits;
import src.engine.Moves.Moves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        Moves move = new Moves(board);

        long from = Bits.B1;
        long to = Bits.C3;
        
        move.makeMove(from, to);

        board.printBoard();
    }
}
