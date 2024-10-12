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

public class MoveBoard {


    BitBoard bitBoard;

    King king = new King();
    Queen queen = new Queen();
    Knight knight = new Knight();
    Pawn pawn = new Pawn();
    Bishop bishop = new Bishop();
    Rook rook = new Rook();
    
    public MoveBoard(BitBoard bitBoard){
        this.bitBoard = bitBoard;
    }

    public long getAttackBoard(PlayerColor playerColor) {

        long attack_board = 0L;
        
        long get_board = bitBoard.getOccSquaresByColor(playerColor);

        long unOcc = bitBoard.getUnOcc();
        long get_opponent_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor())| unOcc;
        long position;

        for(long i = 0; i < 64; i++){
            if((get_board & (1L << i)) != 0){
                position = 1L << i;
                PiecesType piecesType = bitBoard.getPieceType(position);
            
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
        System.out.println("attack board attack board");
        printBoardWithMoves(attack_board);
        return attack_board;
    }


    public void printPossibleMoves(long possibleMoves) {
        String binaryString = Long.toBinaryString(possibleMoves);
        binaryString = String.format("%64s", binaryString).replace(' ', '0');
        System.out.println("Possible Moves (Binary):");
        System.out.println(binaryString);
    }

    // for debugging
    public void printBoardWithMoves(long possibleMoves) {
        if(possibleMoves == 0L){
            System.out.println("No possible moves");
        }
        long fullBoard = 0xFFFFFFFFFFFFFFFFL;
        long mask = 1L;
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < 64; i++) {
            if ((possibleMoves & mask) != 0) {
                int row = 7 - (i / 8);
                int col = i % 8;
                board[row][col] = '*';
            }
            mask <<= 1;
        }

        System.out.println("Board with Possible Moves:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
