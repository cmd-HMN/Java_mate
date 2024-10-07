package src.engine.Interfaces;

@FunctionalInterface
public interface PieceMove {
    long apply(long from, long to, long board);
}
