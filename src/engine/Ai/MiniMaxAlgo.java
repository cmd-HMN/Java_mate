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
    MainInterface mainInterface = new MainInterface();
    FeaturedMoves featuredMoves = new FeaturedMoves(bitBoard, mainInterface);

    public MiniMaxAlgo(BitBoard board){
        this.bitBoard = board;
    }
    public int minMaxAlgo(int depth, boolean isMaximizing) {
        if(depth == 0){
            return 0;
        }

        if(isMaximizing){
            int max_eval = Integer.MIN_VALUE;
            for(int[] move: getPossibleMoves(false)){
                applyMove(move[0], move[1], PlayerColor.BLACK);
                int eval = minMaxAlgo(depth - 1, false);
                undoMove(move[1], move[0], PlayerColor.BLACK);
                max_eval = Math.max(eval, max_eval);
            }
            return max_eval;
        }
        else{
            int min_eval = Integer.MAX_VALUE;
            for(int[] move: getPossibleMoves(false)){
                applyMove(move[0], move[1], PlayerColor.BLACK);
                int eval = minMaxAlgo(depth - 1, true);
                undoMove(move[1], move[0], PlayerColor.BLACK);
                min_eval = Math.min(eval, min_eval);
            }
            return min_eval;
        }
    }

    public List<int[]> getPossibleMoves(boolean isWhite){
        List<int[]> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 64; i++){
            if((bitBoard.getOccSquaresByColor(isWhite? PlayerColor.WHITE : PlayerColor.BLACK) & (1L << i))!= 0){
                PiecesType pieceType = bitBoard.getPieceType(1L << i);
                long empty = bitBoard.getUnOcc();
                long opponentBoard = bitBoard.getOccSquaresByColor(isWhite ? PlayerColor.BLACK : PlayerColor.WHITE);
                long move = mainInterface.getPossibilities(pieceType,isWhite? PlayerColor.WHITE : PlayerColor.BLACK, i, empty, opponentBoard);

                while (move != 0) {
                    int to = Long.numberOfTrailingZeros(move);
                    possibleMoves.add(new int[]{i, to});
                    move &= move - 1;
                }
            }
        }
        return possibleMoves;
    }

    public void applyMove(int from, int to, PlayerColor playerColor){
        featuredMoves.makeMove(from, to, playerColor == PlayerColor.WHITE ? 0: 1, false, false);
    }

    public void undoMove(int from, int to, PlayerColor playerColor){
        featuredMoves.makeMove(from, to, playerColor == PlayerColor.WHITE ? 0: 1, false, false);
    }
}