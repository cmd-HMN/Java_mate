import java.util.Scanner;

import src.engine.BitBoard;
import src.engine.Bits.Bits;
import src.engine.Moves.Moves;

public class Main extends BitBoard {
    public static void main(String[] args) {
        BitBoard board = new BitBoard();
        Moves move = new Moves(board);
        Scanner scanner = new Scanner(System.in);
        Bits bit = new Bits();

        String from, to;

        while (true) {
            System.out.println("Enter the turn: ");
            int player = scanner.nextInt();
            System.out.println("Enter the pawn from: ");
            from = scanner.next();
            System.out.println("Enter the pawn to: ");
            to = scanner.next();
            long from_bits = bit.getBits(from);
            long to_bits = bit.getBits(to);
            move.makeMove(from_bits, to_bits, player);
            board.printBoard();
        }


    }
}
