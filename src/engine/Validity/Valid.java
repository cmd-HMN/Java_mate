package src.engine.Validity;

import src.engine.BitBoard;
import src.engine.ChessPieces.King;
import src.engine.Interfaces.MainInterface;
import src.engine.Moves.AttackBoard;
import src.engine.Moves.FeaturedMoves;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

// this class help us to check different valid conditions   
public class Valid {

    King king = new King();
    private BitBoard bitBoard;
    private AttackBoard attack_board;
    private FeaturedMoves featuredMoves;
    private MainInterface mainInterface;

    public Valid(BitBoard bitBoard, FeaturedMoves featuredMoves, MainInterface mainInterface) {
        this.bitBoard = bitBoard;
        this.attack_board = new AttackBoard(bitBoard);
        this.featuredMoves = featuredMoves;
        this.mainInterface = mainInterface;
    }

    // to check if the pawn has move double squares
    public boolean isDoubleSquare(long from, long to, PlayerColor playerColor) {
        if (playerColor == PlayerColor.WHITE) {
            return (from & 0x000000000000FF00L) != 0 && (to & 0x00000000FF000000L) != 0;
        } else {
            return (from & 0x00FF000000000000L) != 0 && (to & 0x000000FF00000000L) != 0;
        }
    }

    public boolean kingInCheck(PlayerColor playerColor) {
        long king = bitBoard.getBitBoard(PiecesType.KING, playerColor);

        if ((attack_board.getAttackBoard(playerColor.getOppositeColor()) & king) != 0) {
            return true;
        }
        return false;
    }

    public boolean checkmate(PlayerColor playerColor) {

        if (!kingInCheck(playerColor)) {
            return false;
        }

        long get_board_king = bitBoard.getBitBoard(PiecesType.KING, playerColor);
        long king_position = 0L;
        for (int i = 0; i < 64; i++) {
            if ((get_board_king & (1L << i)) != 0) {
                king_position |= (1L << i);
            }
        }
        long get_board = bitBoard.getOccSquaresByColor(playerColor);

        long get_opponent_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());
        long get_unOcc = bitBoard.getUnOcc();

        long get_board_king_move = playerColor == PlayerColor.WHITE
                ? king.white_get_possible_pieces(king_position, get_unOcc, get_opponent_board)
                : king.black_get_possible_pieces(king_position, get_unOcc, get_opponent_board);
        long get_attack_board = attack_board.getAttackBoard(playerColor.getOppositeColor());

        get_board_king_move = get_board_king_move & ~get_attack_board;

        System.out.println("Get_board");
        bitBoard.printBoardWithMoves(get_board);

        boolean cond = capturePiece(playerColor, get_board_king, get_opponent_board, get_unOcc, get_board,
                king_position);
        System.out.println("capture: " + cond);

        boolean cond1 = preventionMove(playerColor, get_board_king, get_opponent_board, get_unOcc, get_board,
        king_position);

        System.out.println("prevention: " + cond1);
        if (get_board_king_move == 0L) {

        }

        return false;
    }

    public boolean capturePiece(PlayerColor playerColor, long get_board_king, long get_opponent_board, long unOcc,
            long get_board, long king_position) {

        for (int i = 0; i < 64; i++) {
            if ((get_opponent_board & (1L << i)) != 0) {
                long attacker_position = 1L << i;
                PiecesType piecesType = bitBoard.getPieceType(attacker_position);

                long attacker_move = mainInterface.getPossibilities(piecesType, playerColor.getOppositeColor(),
                        attacker_position, unOcc, get_board);

                if ((attacker_move & king_position) != 0) {
                    for (int j = 0; j < 64; j++) {
                        if ((get_board & (1L << j)) != 0) {
                            long defender_position = 1L << j;
                            PiecesType piecesType2 = bitBoard.getPieceType(defender_position);

                            long defender_move = mainInterface.getPossibilities(piecesType2, playerColor,
                                    defender_position, unOcc, get_opponent_board);

                            if ((defender_move & attacker_position) != 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean preventionMove(PlayerColor playerColor, long get_board_king, long get_opponent_board,
            long unOcc, long get_board, long king_position) {

        for (int i = 0; i < 64; i++) {
            if ((get_opponent_board & (1L << i)) != 0) {
                long attacker_position = 1L << i;

                PiecesType piecesType = bitBoard.getPieceType(attacker_position);

                long attacker_move = mainInterface.getPossibilities(piecesType, playerColor.getOppositeColor(),
                        attacker_position, unOcc, get_board);

                if ((attacker_move & king_position) != 0) {
                    for (int j = 0; j < 64; j++) {
                        if ((get_board & (1L << j)) != 0) {
                            for(int k = 0; k < 64; k++){
                               
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}