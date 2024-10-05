package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardFrame extends JFrame {

    private char[][] pieces;
    private Point selectedPiece;

    public BoardFrame() {
        setTitle("Java Mate");
        setBounds(10, 10, 512, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pieces = new char[8][8];
        initPieces();

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean isWhite = true;

                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        g.setColor(isWhite ? Color.WHITE : Color.BLACK);
                        g.fillRect(x * 64, y * 64, 64, 64);
                        isWhite = !isWhite;

                        if (pieces[y][x] != ' ') {
                            g.setColor(Color.RED);
                            g.drawString(String.valueOf(pieces[y][x]), x * 64 + 32, y * 64 + 32);
                        }
                    }
                    isWhite = !isWhite;
                }
            }
        };

        // Set the default cursor
        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;

                if (selectedPiece == null) {
                    if (pieces[y][x] != ' ') {
                        selectedPiece = new Point(x, y);
                        System.out.println("Selected piece at: " + selectedPiece);
                        System.out.println((y * 8 + x));
                        // Change cursor to hand when selecting a piece
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
                } else {
                    System.out.println("Move piece to: (" + x + ", " + y + ")");
                    System.out.println((y * 8 + x));
                    pieces[y][x] = pieces[selectedPiece.y][selectedPiece.x];
                    pieces[selectedPiece.y][selectedPiece.x] = ' ';
                    selectedPiece = null;
                    boardPanel.repaint();
                    // Reset cursor to default after move
                    boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        // Mouse motion listener to change cursor when hovering over a piece
        boardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (pieces[y][x] != ' ') {
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } else {
                    boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        boardPanel.setPreferredSize(new Dimension(512, 512));
        add(boardPanel);
        pack();
        setVisible(true);
    }

    private void initPieces() {
        pieces[0][0] = 'R';
        pieces[0][1] = 'N';
        pieces[0][2] = 'B';
        pieces[0][3] = 'Q';
        pieces[0][4] = 'K';
        pieces[0][5] = 'B';
        pieces[0][6] = 'N';
        pieces[0][7] = 'R';

        for (int i = 0; i < 8; i++) {
            pieces[1][i] = 'P';
            pieces[6][i] = 'p';
        }

        pieces[7][0] = 'r';
        pieces[7][1] = 'n';
        pieces[7][2] = 'b';
        pieces[7][3] = 'q';
        pieces[7][4] = 'k';
        pieces[7][5] = 'b';
        pieces[7][6] = 'n';
        pieces[7][7] = 'r';
    }
}
