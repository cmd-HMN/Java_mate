package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import src.engine.*;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;
import src.gui.PiecesImage.ChessPiece;

public class BoardFrame extends JFrame {

    BitBoard board = new BitBoard();
    MainInterface mainInterface = new MainInterface();
    FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);
    ChessPiece chessPiece = new ChessPiece();

    private Point selectedPiece;
    private Image dotImage;  
    boolean selectedCursor = false;
    private boolean isWhiteTurn = true;
    private List<Point> possibleMoves = new ArrayList<>();


    public BoardFrame() {
        setTitle("Java Mate");
        setBounds(10, 10, 512, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{
            dotImage = ImageIO.read(new File("src/gui/assets/dot.png"));
            dotImage = dotImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        }catch (IOException e) {
            e.printStackTrace();
        }

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean isWhite = true;
                long mask;

                for (int y = 7; y >= 0; y--) {
                    for (int x = 0; x < 8; x++) {
                        g.setColor(isWhite ? Color.WHITE : Color.BLUE);
                        g.fillRect(x * 64, (7 - y) * 64, 64, 64);
                        isWhite = !isWhite;
                        mask = 1L << ((y * 8) + x);

                        if ((board.whitePawns & mask) != 0) {
                            g.drawImage(chessPiece.white_pawn ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteKings & mask) != 0){
                            g.drawImage(chessPiece.white_king ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteKnights & mask) != 0){
                            g.drawImage(chessPiece.white_knight ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteBishops & mask) != 0){
                            g.drawImage(chessPiece.white_bishop ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteRooks & mask) != 0){
                            g.drawImage(chessPiece.white_rook ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteQueens & mask) != 0){
                            g.drawImage(chessPiece.white_queen ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.whiteKnights & mask) != 0){
                            g.drawImage(chessPiece.white_knight ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackPawns & mask) != 0){
                            g.drawImage(chessPiece.black_pawn ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackKings & mask) != 0){
                            g.drawImage(chessPiece.black_king ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackKnights & mask) != 0){
                            g.drawImage(chessPiece.black_knight ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackBishops & mask) != 0){
                            g.drawImage(chessPiece.black_bishop ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackRooks & mask) != 0){
                            g.drawImage(chessPiece.black_rook ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                        else if((board.blackQueens & mask) != 0){
                            g.drawImage(chessPiece.black_queen ,x * 64, (7 - y) * 64, 64, 64, this);
                        }
                    }
                    isWhite = !isWhite;
                }
                
                for (Point move : possibleMoves) {
                    int dotX = move.x * 64 + 23; 
                    int dotY = (7 - move.y) * 64 + 23; 
                    g.drawImage(dotImage, dotX, dotY, this);  
                }
            }
        };

        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / 64;
                int y = e.getY() / 64;
                System.out.println(x);
                System.out.println(y);

                if (selectedPiece == null) {
                            selectedPiece = new Point(x, 7 - y);
                            selectedCursor = true;
                            long from = 1L << ((selectedPiece.y * 8) + selectedPiece.x);
                            long moveBoard = featuredMoves.getAllMoves(from, isWhiteTurn ? 0 : 1);
                            possibleMoves.clear(); 
                            for (int i = 0; i < 64; i++) {
                                if ((moveBoard & (1L << i)) != 0) {  
                                    int xx = i % 8;  
                                    int yy = i / 8; 
                                    possibleMoves.add(new Point(xx, yy));  
                                }
                            }
                            if(isWhiteTurn == featuredMoves.isWhiteTurn(from)){
                                boardPanel.repaint();  
                                System.out.println("Selected piece at: " + selectedPiece);
                                boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            }else{
                                System.out.println("It is " + (isWhiteTurn ? "White" : "Black") + " moves.");
                                selectedCursor = false;
                                selectedPiece = null;
                            }
                } else {
                    System.out.println("Move piece to: (" + x + ", " + (7 - y) + ")");
                    long from = 1L << ((selectedPiece.y * 8) + selectedPiece.x);
                    long to = 1L << (((7 - y) * 8) + x);

                    boolean move = featuredMoves.makeMove(from, to, isWhiteTurn ? 0 : 1);

                    if (move) {
                        selectedPiece = null;
                        selectedCursor = false;
                        isWhiteTurn = !isWhiteTurn;
                        possibleMoves.clear();  
                        boardPanel.repaint();  
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    } else {
                        System.out.println("Invalid move");
                        selectedPiece = null;
                        selectedCursor = false;
                        possibleMoves.clear();  
                        boardPanel.repaint();  
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                }
            }
        });

        boardPanel.setPreferredSize(new Dimension(512, 512));
        add(boardPanel);
        pack();
        setVisible(true);
    }
}
