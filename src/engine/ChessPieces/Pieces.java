package src.engine.ChessPieces;

//isn't implemented yet
abstract class Pieces {
    
    public abstract long white_possible_moves(long move, long empty);

    public abstract long black_possible_moves(long move, long empty);

    public abstract long white_possible_attack(long move, long black_occ);

    public abstract long black_possible_attack(long move, long white_occ);
    
    public abstract long white_get_possible_pieces(long move, long empty, long black_occ);

    public abstract long black_get_possible_pieces(long move, long empty, long black_occ);

}