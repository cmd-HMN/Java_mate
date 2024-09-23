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

    BitBoard bitBoard = new BitBoard();
    King king = new King();
    Queen queen = new Queen();
    Knight knight = new Knight();
    Pawn pawn = new Pawn();
    Bishop bishop = new Bishop();
    Rook rook = new Rook();

    public long getAttackBoard(PlayerColor playerColor) {

        long attack_board = 0L;
        long get_board = bitBoard.getOccSquaresByColor(playerColor);

        for(long i = 0; i < 64; i++){
            if((get_board & (1L << i)) != 0){
                PiecesType piecesType = bitBoard.getPieceType(i);
                long unOcc = bitBoard.getUnOcc(i);
                long get_opponent_board = bitBoard.getBitBoard(piecesType, playerColor);
            
                if(playerColor == PlayerColor.WHITE){

                    switch(piecesType){
                        
                        case KING:
                            attack_board |= king.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        case QUEEN:
                            attack_board |= queen.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        case KNIGHT:
                            attack_board |= knight.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        case BISHOP:
                            attack_board |= bishop.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        case ROOK:
                            attack_board |= rook.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        case PAWN:
                            attack_board |= pawn.white_possible_attack(i, get_opponent_board, unOcc);
                            break;

                        default:
                            break;
                    }

                }else{
                switch(piecesType){

                    case KING:
                        attack_board |= king.black_possible_attack(i, get_opponent_board, unOcc);
                        break;

                    case QUEEN:
                        attack_board |= queen.black_possible_attack(i, get_opponent_board, unOcc);
                        break;

                    case KNIGHT:
                        attack_board |= knight.black_possible_attack(i, get_opponent_board, unOcc);
                        break;

                    case BISHOP:
                        attack_board |= bishop.black_possible_attack(i, get_opponent_board, unOcc);
                        break;

                    case ROOK:
                        attack_board |= rook.black_possible_attack(i, get_opponent_board, unOcc);
                        break;

                    case PAWN:
                        attack_board |= pawn.black_possible_attack(i, get_opponent_board, unOcc);
                        printBoardWithMoves(pawn.black_possible_attack((i << 1L), get_opponent_board, unOcc));
                        break;

                    default:
                        break;
                }
            }
            }
        }
        printBoardWithMoves(attack_board);
        return attack_board;

    }

     // for debugging
     public void printPossibleMoves(long possibleMoves) {
        String binaryString = Long.toBinaryString(possibleMoves);
        binaryString = String.format("%64s", binaryString).replace(' ', '0');
        System.out.println("Possible Moves (Binary):");
        System.out.println(binaryString);
    }

    public void printBoardWithMoves(long possibleMoves) {
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
