package src.engine.Ai;

import java.util.ArrayList;
import java.util.List;

import src.engine.BitBoard;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

public class MiniMaxAlgo {

    private BitBoard bitBoard;
    private MainInterface mainInterface;
    private FeaturedMoves featuredMoves;

    public MiniMaxAlgo(BitBoard board, MainInterface mainInterface, FeaturedMoves FeaturedMoves){
        this.bitBoard = board;
        this.mainInterface = mainInterface;
        this.featuredMoves = FeaturedMoves;
    }
    public int minMaxAlgo(int depth, boolean isMaximizing) {
        if(depth == 0){
            return 0;
        }

        if(isMaximizing){
            int max_eval = Integer.MIN_VALUE;

        }
        return 0;
    }

    public List<Long> getPossibleMoves(boolean isWhite){
        List<Long> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 64; i++){
            if((bitBoard.getOccSquaresByColor(isWhite? PlayerColor.WHITE : PlayerColor.BLACK) & (1L << i))!= 0){
                PiecesType pieceType = bitBoard.getPieceType(1L << i);
                long empty = bitBoard.getUnOcc();
                long opponentBoard = bitBoard.getOccSquaresByColor(isWhite ? PlayerColor.BLACK : PlayerColor.WHITE);
                possibleMoves.add(mainInterface.getPossibilities(pieceType,isWhite? PlayerColor.WHITE : PlayerColor.BLACK, i, empty, opponentBoard));
            }
        }
        return possibleMoves;
    }

    public void applyMove(int from, int to, PlayerColor playerColor){
        featuredMoves.makeMove(from, to, playerColor == PlayerColor.WHITE ? 0: 1, false);
        
    }
}