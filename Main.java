import src.engine.BitBoard;
import src.engine.Bits.Bits;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        MainInterface mainInterface = new MainInterface();
        FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);

        featuredMoves.makeMove(Bits.A2, Bits.A4, 0, 0); 
        
        // featuredMoves.makeMove(Bits.A4, Bits.A5, 0, 0); 
        // featuredMoves.makeMove(Bits.A5, Bits.A6, 0, 0); 
        
        // featuredMoves.makeMove(Bits.H7, Bits.H5, 1, 0); 
        // featuredMoves.makeMove(Bits.H5, Bits.H4, 1, 0); 
        // featuredMoves.makeMove(Bits.H4, Bits.H3, 1, 0);
        
        
        // featuredMoves.makeMove(Bits.A1, Bits.A5, 0, 0); 
        // featuredMoves.makeMove(Bits.A5, Bits.H5, 0, 0);
        // featuredMoves.makeMove(Bits.H8, Bits.H5, 1, 1);
        
        // featuredMoves.makeMove(Bits.E2, Bits.E4, 0, 0);
        // featuredMoves.makeMove(Bits.D1, Bits.E2, 0, 0);
        // featuredMoves.makeMove(Bits.E2, Bits.H5, 0, 1);
        






        
        // featuredMoves.makeMove(Bits.A5, Bits.H5, 0, 1); 
        
        // featuredMoves.makeMove(Bits.D7, Bits.D5, 1, 0); 
        // featuredMoves.makeMove(Bits.E4, Bits.E5, 0, 0);
        // featuredMoves.makeMove(Bits.F7, Bits.F5, 1, 0); 
        // featuredMoves.makeMove(Bits.E5, Bits.F6, 0, 2); 
        // featuredMoves.makeMove(Bits.F6, Bits.G7, 0, 1); 
        // featuredMoves.makeMove(Bits.G7, Bits.H8, 0, 3); 
        // featuredMoves.makeMove(Bits.H8, Bits.G6, 0, 0); 

        
    }   
}