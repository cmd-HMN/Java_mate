package src.engine.Validity;

import src.engine.Type.PlayerColor;

// this class help us to check different valid conditions   
public class Valid {

    //to check if the pawn has move double squares
    public boolean isDoubleSquare(long from, long to, PlayerColor playerColor){
        if(playerColor == PlayerColor.WHITE){
            System.out.println("White Pawn Move");
            System.out.println("From: " + Long.toBinaryString(from));
            System.out.println("To: " + Long.toBinaryString(to));
            return (from & 0x000000000000FF00L) != 0 && (to & 0x00000000FF000000L) != 0;
        }
        else{
            System.out.println("Black Pawn Move");
            System.out.println("From: " + Long.toBinaryString(from));
            System.out.println("To: " + Long.toBinaryString(to));
            return (from & 0x00FF000000000000L) != 0 && (to & 0xFF00000000000000L) != 0;
        }
    }
}
