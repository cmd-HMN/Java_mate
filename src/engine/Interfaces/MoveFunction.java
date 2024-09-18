package src.engine.Interfaces;


// It defined the structure of the move function
@FunctionalInterface
public interface MoveFunction {
    long apply(long from, long empty);
}