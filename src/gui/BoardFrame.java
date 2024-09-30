package src.gui;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {
    private JButton[][] board = new JButton[8][8];  
    private boolean isWhite = true;
    
    public void Board() {
        setTitle("Java mate");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        boolean isWhite = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();

                if (isWhite) {
                    button.setBackground(java.awt.Color.white);
                } else {
                    button.setBackground(java.awt.Color.black);
                }
           
                button.setOpaque(true);          
                button.setBorderPainted(false);   
                boardPanel.add(button);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
        add(boardPanel);
        
        setVisible(true);
    }

}
