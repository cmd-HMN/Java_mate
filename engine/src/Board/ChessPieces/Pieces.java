package engine.src.Board.ChessPieces;

//isn't implemented yet
abstract class Pieces {
    public abstract long white_get_possible_piece(long move, long empty);

    public abstract long black_get_possible_piece(long move, long empty);

    public abstract long white_possible_attack(long move, long empty);

    public abstract long black_possible_attack(long move, long empty);

    public abstract long white_possible_capture(long move, long empty);

    public abstract long black_possible_capture(long move, long empty);
}