package src.engine.Validity;

import src.engine.BitBoard;
import src.engine.ChessPieces.King;
import src.engine.Moves.AttackBoard;
import src.engine.Moves.FeaturedMoves;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

// this class help us to check different valid conditions   
public class Valid {

    King king = new King();
    private BitBoard bitBoard;
    private AttackBoard attack_board;
    private FeaturedMoves  featuredMoves;

    public Valid(BitBoard bitBoard, FeaturedMoves featuredMoves){
        this.bitBoard = bitBoard;
        this.attack_board = new AttackBoard(bitBoard);
        this.featuredMoves = featuredMoves;
    }
    
    //to check if the pawn has move double squares
    public boolean isDoubleSquare(long from, long to, PlayerColor playerColor){
        if(playerColor == PlayerColor.WHITE){
            return (from & 0x000000000000FF00L) != 0 && (to & 0x00000000FF000000L) != 0;
        }
        else{
            return (from & 0x00FF000000000000L) != 0 && (to & 0x000000FF00000000L) != 0;
        }
    }

    public boolean kingInCheck(PlayerColor playerColor){
        long king = bitBoard.getBitBoard(PiecesType.KING, playerColor);

        if((attack_board.getAttackBoard(playerColor.getOppositeColor()) & king) != 0){
            return true;
        }
        return false;
    }

    public boolean checkmate(PlayerColor playerColor){

        if(!kingInCheck(playerColor)){
            return false;
        }
        long get_board_king = bitBoard.getBitBoard(PiecesType.KING, playerColor);
        long from = 0L;
        for(int i = 0; i < 64; i++){
            if((get_board_king & (1L << i)) != 0){
                from |= (1L << i);
            }
        }
        
        long get_opponent_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());
        long get_unOcc = bitBoard.getUnOcc();

        long get_board_king_move = playerColor == PlayerColor.WHITE ?
        king.white_get_possible_pieces(from, get_unOcc, get_opponent_board) :
        king.black_get_possible_pieces(from, get_unOcc, get_opponent_board);
        long get_attack_board = attack_board.getAttackBoard(playerColor.getOppositeColor());

        System.out.println("King Move");
        get_board_king_move = get_board_king_move & ~get_attack_board;
        bitBoard.printBoardWithMoves(get_board_king_move);
        // bitBoard.printBoardWithMoves(get_attack_board);


        long possible_move = featuredMoves.getAllPossibleMove(playerColor);
        // bitBoard.printBoardWithMoves(possible_move);

        long prevention_board = get_attack_board & possible_move;
        // bitBoard.printBoardWithMoves(prevention_board);

        if(kingInCheck(playerColor) && possible_move == 0L){
            return true;
        }

        return false;
    }
}