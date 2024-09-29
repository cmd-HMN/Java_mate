package src.engine.History;

import java.util.Stack;

import src.engine.Bits.Bits;

// this will be used to track the history of move (yeah just like your chrome history, not including your're incognito history)
public class History {
    private static Stack<Bits> history = new Stack<Bits>();

    public static void addHistory(Bits bits) { history.push(bits);}

}