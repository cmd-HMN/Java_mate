package src.engine.Validity;

import src.engine.BitBoard;
import src.engine.ChessPieces.King;
import src.engine.Moves.AttackBoard;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

// this class help us to check different valid conditions   
public class Valid {

    King king = new King();
    private BitBoard bitBoard;
    private AttackBoard attack_board;
    public Valid(BitBoard bitBoard){
        this.bitBoard = bitBoard;
        this.attack_board = new AttackBoard(bitBoard);
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
        long get_board_king = bitBoard.getBitBoard(PiecesType.KING, playerColor);
        long king_position = 0L;
        for(int i = 0; i < 64; i++){
            if((get_board_king & (1L << i)) != 0){
                king_position = (1L << i);
                break;
            }
        }

        long get_occ = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());

        // System.out.println("OCC");
        // bitBoard.printBoardWithMoves(get_occ);
        
        long get_unOcc = bitBoard.getUnOcc();

        // System.out.println("UNOCC");
        // bitBoard.printBoardWithMoves(get_unOcc);

        long possible_move = playerColor == PlayerColor.WHITE ? king.white_get_possible_pieces(king_position, get_occ, get_unOcc) : king.black_get_possible_pieces(king_position, get_occ, get_unOcc);
        // System.out.println("POSSIBLE MOVE");
        // bitBoard.printBoardWithMoves(possible_move);

        if(kingInCheck(playerColor) && possible_move == 0L){
            return true;
        }
        return false;
    }
}
