package src.engine.Interfaces;

// It defined the structure of the attack function 
@FunctionalInterface
public interface AttackFunction {
    long apply(long from, long to, long board);
}