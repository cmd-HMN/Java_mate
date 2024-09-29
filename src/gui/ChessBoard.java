package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javafx.scene.paint.Color;

public class ChessBoard extends JFrame {
    private JButton[][] board = new JButton[8][8];  
    private boolean isWhite = true;
    
    public ChessBoard() {
        // Setting up the frame
        setTitle("Chess Board");
        setSize(600, 600); // Adjust size as needed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create a panel with an 8x8 grid layout
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        // Adding 64 buttons to the panel
        boolean isWhite = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                
                // Alternate colors like a chessboard
                if (isWhite) {
                    button.setBackground(java.awt.Color.white);
                } else {
                    button.setBackground(java.awt.Color.black);
                }
                
                // Ensure the background color is applied
                button.setOpaque(true);            // Makes background visible
                button.setBorderPainted(false);    // Removes the default border

                // Add button to the panel
                boardPanel.add(button);
                
                // Toggle color for the next button
                isWhite = !isWhite;
            }
            // After every row, start with the opposite color
            isWhite = !isWhite;
        }
        
        // Add the panel to the frame
        add(boardPanel);
        
        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create the chessboard
        new ChessBoard();
    }
}
