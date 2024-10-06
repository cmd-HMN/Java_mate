package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import src.engine.*;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;

public class BoardFrame extends JFrame {

    BitBoard board = new BitBoard();
    MainInterface mainInterface = new MainInterface();
    FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);

    private Image[][] pieces;
    private Point selectedPiece;
    boolean selectedCursor = false;
    private boolean isWhiteTurn = true;

    public BoardFrame() {
        setTitle("Java Mate");
        setBounds(10, 10, 512, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pieces = new Image[8][8];
        initPieces();

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean isWhite = true;

                for (int y = 7; y >= 0; y--) {
                    for (int x = 0; x < 8; x++) {
                        g.setColor(isWhite ? Color.WHITE : Color.BLUE);
                        g.fillRect(x * 64, (7 - y) * 64, 64, 64);
                        isWhite = !isWhite;

                        // Draw piece images
                        if (pieces[y][x] != null) {
                            g.drawImage(pieces[y][x], x * 64, (7 - y) * 64, 64, 64, this);
                        }
                    }
                    isWhite = !isWhite;
                }
            }
        };

        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;

                if (selectedPiece == null) {
                    if (pieces[7 - y][x] != null) {
                        selectedPiece = new Point(x, 7 - y);
                        selectedCursor = true;
                        System.out.println("Selected piece at: " + selectedPiece);
                    }
                } else {
                    System.out.println("Move piece to: (" + x + ", " + (7 - y) + ")");
                    long from = 1L << ((selectedPiece.y * 8) + selectedPiece.x);
                    long to = 1L << (((7 - y) * 8) + x);

                    boolean move = featuredMoves.makeMove(from, to, isWhiteTurn ? 0 : 1);

                    if (move) {
                        pieces[7 - y][x] = pieces[selectedPiece.y][selectedPiece.x];
                        pieces[selectedPiece.y][selectedPiece.x] = null;
                        selectedPiece = null;
                        selectedCursor = false;
                        isWhiteTurn = !isWhiteTurn;
                        boardPanel.repaint();
                    } else {
                        System.out.println("Invalid move");
                        selectedPiece = null;
                        selectedCursor = false;
                    }
                }
            }
        });

        boardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;

                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (pieces[7 - y][x] != null) {
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
        try {
            // Load white pieces
            pieces[0][0] = ImageIO.read(new File("src/gui/assets/rook_white.png"));
            pieces[0][1] = ImageIO.read(new File("src/gui/assets/knight_white.png"));
            pieces[0][2] = ImageIO.read(new File("src/gui/assets/bishop_white.png"));
            pieces[0][3] = ImageIO.read(new File("src/gui/assets/queen_white.png"));
            pieces[0][4] = ImageIO.read(new File("src/gui/assets/king_white.png"));
            pieces[0][5] = ImageIO.read(new File("src/gui/assets/bishop_white.png"));
            pieces[0][6] = ImageIO.read(new File("src/gui/assets/knight_white.png"));
            pieces[0][7] = ImageIO.read(new File("src/gui/assets/rook_white.png"));

            for (int i = 0; i < 8; i++) {
                pieces[1][i] = ImageIO.read(new File("src/gui/assets/pawn_white.png"));
                pieces[6][i] = ImageIO.read(new File("src/gui/assets/pawn_black.png"));
            }

            // Load black pieces
            pieces[7][0] = ImageIO.read(new File("src/gui/assets/rook_black.png"));
            pieces[7][1] = ImageIO.read(new File("src/gui/assets/knight_black.png"));
            pieces[7][2] = ImageIO.read(new File("src/gui/assets/bishop_black.png"));
            pieces[7][3] = ImageIO.read(new File("src/gui/assets/queen_black.png"));
            pieces[7][4] = ImageIO.read(new File("src/gui/assets/king_black.png"));
            pieces[7][5] = ImageIO.read(new File("src/gui/assets/bishop_black.png"));
            pieces[7][6] = ImageIO.read(new File("src/gui/assets/knight_black.png"));
            pieces[7][7] = ImageIO.read(new File("src/gui/assets/rook_black.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
