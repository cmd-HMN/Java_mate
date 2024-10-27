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
        boolean cond1 = preventionMove(playerColor, get_board_king, get_opponent_board, get_unOcc, get_board,
        king_position);
        boolean cond2 = isAvoidingMove(playerColor, get_board_king_move, king_position);

        System.out.println("capture: " + cond);
        System.out.println("prevention: " + cond1);
        System.out.println("avoiding: " + cond2);


        bitBoard.printBoardWithMoves(get_board_king_move);
        if (!cond2) {
            if(cond == true){
                if(cond1 == false){
                    System.out.println("Checkmate: " + playerColor);
                    return true;
                }
            }
            
            if((cond && cond1) == false){
                System.out.println("Checkmate: " + playerColor);
                return true;
            }
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

        boolean move;
        for (int i = 0; i < 64; i++) {
            if ((get_opponent_board & (1L << i)) != 0) {
                long attacker_position = 1L << i;

                PiecesType piecesType = bitBoard.getPieceType(attacker_position);

                long attacker_move = mainInterface.getPossibilities(piecesType, playerColor.getOppositeColor(),
                        attacker_position, unOcc, get_board);

                if ((attacker_move & king_position) != 0) {
                    for (int j = 0; j < 64; j++) {
                        long from = 1L << j;
                        if ((get_board & (1L << j)) != 0) {
                            for(int k = 0; k < 64; k++){
                                long to = 1L << k;
                                if((unOcc & to) != 0){
                                    int getMoveType = bitBoard.getMoveType(from, to);
                                    if (getMoveType == 0) {
                                        if (playerColor == PlayerColor.WHITE) {
                                            move = featuredMoves.normal(from, to, PlayerColor.WHITE, true);
                                            if (!move) {
                                                continue;
                                            }
                                            return true;
                                        } else if (playerColor == PlayerColor.BLACK) {
                                            move = featuredMoves.normal(from, to, PlayerColor.BLACK, true);
                                            if (!move) {
                                                continue;
                                            }
                                            return true;
                                        }
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public boolean isAvoidingMove(PlayerColor playerColor, long get_board_king_move, long king_position){
        boolean move;
        for(int i = 0; i < 64; i++){
            if((get_board_king_move & (1L << i)) != 0){
                long to = 1L << i;
                int getMoveType = bitBoard.getMoveType(king_position, to);
                if (getMoveType == 0) {
                    if (playerColor == PlayerColor.WHITE) {
                        move = featuredMoves.normal(king_position, to, PlayerColor.WHITE, true);
                        if (!move) {
                            continue;
                        }
                        return true;
                    } else if (playerColor == PlayerColor.BLACK) {
                        move = featuredMoves.normal(king_position, to, PlayerColor.BLACK, true);
                        if (!move) {
                            continue;
                        }
                        return true;
                    }
                }
                if (getMoveType == 1) {
                    if (playerColor == PlayerColor.WHITE) {
                        move = featuredMoves.capture(king_position, to, PlayerColor.WHITE);
                        if (!move) {
                            continue;
                        }
                        return true;
                    } else if (playerColor == PlayerColor.BLACK) {
                        move = featuredMoves.capture(king_position, to, PlayerColor.BLACK);
                        if (!move) {
                            continue;
                        }
                        return true;
                    }
                }

                
            }
        }
        return false;
    }
}