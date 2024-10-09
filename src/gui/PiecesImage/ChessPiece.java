package src.gui.PiecesImage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;


public class ChessPiece {
    public BufferedImage white_king, 
    white_queen,
    white_bishop,
    white_knight,
    white_rook,
    white_pawn,
    black_king,
    black_queen,
    black_bishop,
    black_knight,
    black_rook,
    black_pawn
    ,dotImage;


    public ChessPiece(){
        try{
            white_pawn = ImageIO.read(new File("src/gui/assets/pawn_white.png"));
            white_king = ImageIO.read(new File("src/gui/assets/king_white.png")); 
            white_queen = ImageIO.read(new File("src/gui/assets/queen_white.png"));
            white_bishop = ImageIO.read(new File("src/gui/assets/bishop_white.png"));
            white_knight = ImageIO.read(new File("src/gui/assets/knight_white.png"));
            white_rook = ImageIO.read(new File("src/gui/assets/rook_white.png"));
            black_king = ImageIO.read(new File("src/gui/assets/king_black.png"));
            black_queen = ImageIO.read(new File("src/gui/assets/queen_black.png"));
            black_bishop = ImageIO.read(new File("src/gui/assets/bishop_black.png"));
            black_knight = ImageIO.read(new File("src/gui/assets/knight_black.png"));
            black_rook = ImageIO.read(new File("src/gui/assets/rook_black.png"));
            black_pawn = ImageIO.read(new File("src/gui/assets/pawn_black.png"));



            dotImage = ImageIO.read(new File("src/gui/assets/dot.png"));
            dotImage.getScaledInstance(5, 5, Image.SCALE_SMOOTH);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
