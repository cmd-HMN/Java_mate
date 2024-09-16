import java.util.Scanner;

import src.engine.BitBoard;
import src.engine.Bits.Bits;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BitBoard board = new BitBoard();
        MainInterface mainInterface = new MainInterface();
        FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);
        Bits bit = new Bits();
        String from, to;


        while (true) {
            System.out.println("Enter the turn: ");
            int player = scanner.nextInt();
            System.out.println("Enter the move type: ");
            int moveType = scanner.nextInt();
            System.out.println("Enter the pawn from: ");
            from = scanner.next();
            System.out.println("Enter the pawn to: ");
            to = scanner.next();
            long from_bits = bit.getBits(from);
            long to_bits = bit.getBits(to);
            featuredMoves.makeMove(from_bits, to_bits, player, moveType);
        }


    }
    
}
