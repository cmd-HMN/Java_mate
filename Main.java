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

        wPawn = move.PawnMove(from_move, to, to, wPawn);

        board.setWhitePawns(wPawn);

        board.printBoard();

        long from_ = Bits.E1;
        long to_ = Bits.E2;
        long wKing = board.wGetKing();
        long king = move.KingMove(from_, to_, to, wKing);
        
        board.setKing(king);
        board.printBoard();

        long from___ = Bits.B1;
        long to___ = Bits.C3;

        long knight = board.wGetKnight();

        knight = move.KnightMove(from___, to___, to___, knight);

        board.setKnights(knight);

        board.printBoard();
    }
}
