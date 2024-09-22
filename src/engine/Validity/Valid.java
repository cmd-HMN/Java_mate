package src.engine.Validity;

import src.engine.Type.PlayerColor;

// this class help us to check different valid conditions   
public class Valid {

    //to check if the pawn has move double squares
    public boolean isDoubleSquare(long from, long to, PlayerColor playerColor){
        if(playerColor == PlayerColor.WHITE){
            return (from & 0x000000000000FF00L) != 0 && (to & 0x00000000FF000000L) != 0;
        }
        else{
            return (from & 0x00FF000000000000L) != 0 && (to & 0x000000FF00000000L) != 0;
        }
    }

    public boolean kingInCheck(){
        return false;
    }
}
