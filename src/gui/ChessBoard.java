package src.gui;

import javax.swing.SwingUtilities;

public class ChessBoard {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BoardFrame::new);
};
}
