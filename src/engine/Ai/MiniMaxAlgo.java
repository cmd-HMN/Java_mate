package src.engine.Ai;

import java.util.ArrayList;
import java.util.List;

import src.engine.BitBoard;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.FeaturedMoves;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;
import src.engine.eval.Evaluation;

public class MiniMaxAlgo {

    private BitBoard bitBoard;
    MainInterface mainInterface;
    FeaturedMoves featuredMoves;
    Evaluation evaluation;

    public MiniMaxAlgo(BitBoard board){
        this.bitBoard = board;
        this.mainInterface = new MainInterface();
        this.featuredMoves = new FeaturedMoves(board, mainInterface);
        this.evaluation = new Evaluation(board);
    }
    public int minMaxAlgo(int depth, boolean isMaximizing) {

        bitBoard.printBoardWithMoves(bitBoard.getOcc());
        
        if(depth == 0){
            return evaluation.getScore();
        }

        if(isMaximizing){
            int max_eval = Integer.MIN_VALUE;
            for(long[] move: getPossibleMoves(false)){
                applyMove(move[0], move[1], PlayerColor.BLACK);
                int eval = minMaxAlgo(depth - 1, false);
                undoMove(move[1], move[0], PlayerColor.BLACK);
                max_eval = Math.max(eval, max_eval);
            }
            return max_eval;
        }
        else{
            int min_eval = Integer.MAX_VALUE;
            for(long[] move: getPossibleMoves(false)){
                bitBoard.printBoardWithMoves(move[0]);
                bitBoard.printBoardWithMoves(move[1]);
                applyMove(move[0], move[1], PlayerColor.BLACK);
                int eval = minMaxAlgo(depth - 1, true);
                undoMove(move[1], move[0], PlayerColor.BLACK);
                min_eval = Math.min(eval, min_eval);
            }
            return min_eval;
        }
    }

    public List<long[]> getPossibleMoves(boolean isWhite){
        List<long[]> possibleMoves = new ArrayList<>();
        long occ = bitBoard.getOccSquaresByColor(isWhite ? PlayerColor.WHITE : PlayerColor.BLACK);
        for(int i = 0; i < 64; i++){
            if((occ & (1l << i)) != 0){
                PiecesType pieceType = bitBoard.getPieceType(1L << i);
                long empty = bitBoard.getUnOcc();
                long opponentBoard = bitBoard.getOccSquaresByColor(isWhite ? PlayerColor.BLACK : PlayerColor.WHITE);
                long move = mainInterface.getPossibilities(pieceType,isWhite? PlayerColor.WHITE : PlayerColor.BLACK,  i << 1L, empty, opponentBoard);

                while (move != 0) {
                    long to = Long.numberOfTrailingZeros(move);
                    possibleMoves.add(new long[]{1L << i,1L << to});

                    move &= move - 1;
                }
            }
        }
        System.out.println(possibleMoves);
        return possibleMoves;
    }

    public void applyMove(long from, long to, PlayerColor playerColor){
        featuredMoves.makeMove(from << 1L, to << 1L, playerColor == PlayerColor.WHITE ? 0: 1, false, false);
    }

    public void undoMove(long from, long to, PlayerColor playerColor){
        featuredMoves.makeMove(from, to, playerColor == PlayerColor.WHITE ? 0: 1, false, false);
    }
}