package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.engine.*;
import src.engine.Ai.MiniMaxAlgo;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;
import src.engine.Type.PlayerColor;
import src.engine.Validity.Valid;
import src.gui.PiecesImage.ChessPiece;

public class BoardFrame extends JFrame {

    BitBoard board = new BitBoard();
    MainInterface mainInterface = new MainInterface();
    FeaturedMoves featuredMoves = new FeaturedMoves(board, mainInterface);
    ChessPiece chessPiece = new ChessPiece();
    Valid valid = new Valid(board, featuredMoves, mainInterface);
    private JPanel boardPanel;
    
    private Point selectedPiece; 
    boolean selectedCursor = false;
    private boolean isWhiteTurn = true;
    private long moveBoard;
    
    
    public BoardFrame() {
        setTitle("Java Mate");
        setBounds(10, 10, 512, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new JPanel() {
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


                        if((moveBoard & mask) != 0){
                            g.drawImage(chessPiece.dotImage, x * 64, (7 - y) * 64, 64, 64, this);
                        }
                    }
                    isWhite = !isWhite;
                    if(valid.checkmate(isWhite ? PlayerColor.WHITE : PlayerColor.BLACK)){
                        checkMateDialog(isWhite ? 0 : 1);
                        System.exit(0);
                    }
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
                    selectedPiece = new Point(x, 7 - y);
                    selectedCursor = true;
                    long from = 1L << ((selectedPiece.y * 8) + selectedPiece.x);
                    System.out.println(from);
                    moveBoard  = 0L;
                    moveBoard = featuredMoves.getAllMoves(from, isWhiteTurn ? 0 : 1);

                    if(isWhiteTurn == featuredMoves.isWhiteTurn(from)){
                        boardPanel.repaint();  
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }else{
                        System.out.println("It is " + (isWhiteTurn ? "White" : "Black") + " moves.");
                        selectedCursor = false;
                        selectedPiece = null;
                    }
                } else {
                    long from = 1L << ((selectedPiece.y * 8) + selectedPiece.x);
                    long to = 1L << (((7 - y) * 8) + x);

                    boolean move = featuredMoves.makeMove(from, to, isWhiteTurn ? 0 : 1, false, true);
                    System.out.println("move: " + move);
                    if (move) {
                        selectedPiece = null;
                        selectedCursor = false;
                        isWhiteTurn = !isWhiteTurn;
                        moveBoard  = 0L; 
                        boardPanel.repaint();  
                        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    } else {
                        System.out.println("Invalid move");
                        selectedPiece = null;
                        selectedCursor = false;
                        moveBoard  = 0L;  
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


    public static String getPromotionChoice() {
        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
        int choice = JOptionPane.showOptionDialog(
            null, 
            "Choose your promotion piece:", 
            "Pawn Promotion", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options, 
            options[0]);

        return options[choice];
    }

    public void checkMateDialog(int playerColor){
        JOptionPane.showMessageDialog(
            null, 
            "CheckMate", 
            "sd", 
            JOptionPane.NO_OPTION
            );

        remove(boardPanel);
        revalidate();
        repaint();

    }
}
