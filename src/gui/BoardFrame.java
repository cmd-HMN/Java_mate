package src.gui;


import javax.swing.*;

import java.awt.*;
public class BoardFrame extends JFrame{

    public BoardFrame(){
        setTitle("Java Mate");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
 
        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard(){
        Color lightColor = new Color(255, 255, 255);
        Color darkColor = new Color(255, 0, 0);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                square.setPreferredSize(new Dimension(50, 50));

                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }
 
                add(square);
            }
        }
    }
}
