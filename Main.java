
import src.engine.BitBoard;
import src.engine.Bits.Bits;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        MainInterface mainInterface = new MainInterface();
        FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);

        featuredMoves.makeMove(Bits.E2, Bits.E4, 0, 0);
        featuredMoves.makeMove(Bits.E4, Bits.E5, 0, 0);
        featuredMoves.makeMove(Bits.D7, Bits.D5, 1, 0);
        featuredMoves.makeMove(Bits.E5, Bits.D6, 0, 2);
    }
    
}
