import engine.src.Board.BitBoard;
import engine.src.Board.Bits.Bits;
import engine.src.Board.ChessPieces.Pawn;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        Pawn pawn = new Pawn();

        long wPawn = board.getWhitePawns();
        long EMPTY = ~wPawn;

        long from = Bits.E2;

        long to = Bits.E4;


        board.setWhitePawns(pawn.movePawn(from, to, EMPTY, wPawn));

        
        board.printBoard();
    }
}
