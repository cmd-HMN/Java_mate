import engine.src.Board.BitBoard;
import engine.src.Board.Bits.Bits;
import engine.src.Board.Moves.Moves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        Moves move = new Moves(board);
        
        long from_move = Bits.E2;
        long to = Bits.E3;

        long wPawn = board.getWhitePawns();
        long white = move.PawnMove(from_move, to, to, wPawn);
        
        board.setWhitePawns(white);
        board.printBoard();
    }
}
