package src.engine.Interfaces;

@FunctionalInterface
public interface AttackFunction {
    long apply(long from, long to, long board);
}
