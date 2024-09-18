package src.engine.Type;


// all the player color
public enum PlayerColor {
    BLACK,
    WHITE;

    // get the opposite color to the respective color using
    public PlayerColor getOppositeColor(){
        return this == WHITE ? BLACK : WHITE;
    }
}
