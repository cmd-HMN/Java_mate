package src.engine.Moves;

import src.engine.BitBoard;
import src.engine.ChessPieces.Bishop;
import src.engine.ChessPieces.King;
import src.engine.ChessPieces.Knight;
import src.engine.ChessPieces.Pawn;
import src.engine.ChessPieces.Queen;
import src.engine.ChessPieces.Rook;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

public class AttackBoard {


    BitBoard bitBoard;

    King king = new King();
    Queen queen = new Queen();
    Knight knight = new Knight();
    Pawn pawn = new Pawn();
    Bishop bishop = new Bishop();
    Rook rook = new Rook();
    
    public AttackBoard(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }

    public long getAttackBoard(PlayerColor playerColor) {

        long attack_board = 0L;
        
        long get_board = bitBoard.getOccSquaresByColor(playerColor);
        System.out.println(playerColor);

        long get_opponent_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());
        long position;

        for(long i = 0; i < 64; i++){
            if((get_board & (1L << i)) != 0){
                position = 1L << i;
                PiecesType piecesType = bitBoard.getPieceType(position);
                long unOcc = bitBoard.getUnOcc();
            
                if(playerColor == PlayerColor.WHITE){

                    switch(piecesType){
                        
                        case KING:
                            attack_board |= king.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        case QUEEN:
                            attack_board |= queen.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        case KNIGHT:
                            attack_board |= knight.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        case BISHOP:
                            attack_board |= bishop.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        case ROOK:
                            attack_board |= rook.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        case PAWN:
                            attack_board |= pawn.white_possible_attack(position, get_opponent_board, unOcc);
                            break;

                        default:
                            break;
                    }

                }else{
                switch(piecesType){
                    
                    
                    case KING:
                        attack_board |= king.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    case QUEEN:
                        attack_board |= queen.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    case KNIGHT:
                        attack_board |= knight.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    case BISHOP:
                        attack_board |= bishop.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    case ROOK:
                        attack_board |= rook.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    case PAWN:
                        attack_board |= pawn.black_possible_attack(position, get_opponent_board, unOcc);
                        break;

                    default:
                        break;
                }
            }
            }
        }
        return attack_board;
    }
}
