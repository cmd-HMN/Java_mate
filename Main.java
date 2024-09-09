import engine.src.ChessBoard.BitBoard;

public class Main extends BitBoard{
    public static void main(String[] args) {
        BitBoard board = new BitBoard();

        board.setPiece(0);

        board.setPiece(63);

        board.printBoard();
    }
}
