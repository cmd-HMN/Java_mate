package src.engine.Interfaces;

@FunctionalInterface
public interface MoveFunction {
    long apply(long from, long empty);
}