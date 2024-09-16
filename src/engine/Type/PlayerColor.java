package src.engine.Type;

public enum PlayerColor {
    BLACK,
    WHITE;

    public PlayerColor getOppositeColor(){
        return this == WHITE ? BLACK : WHITE;
    }
}
