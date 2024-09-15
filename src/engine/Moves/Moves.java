package src.engine.Moves;

import src.engine.BitBoard;
import src.engine.ChessPieces.King;
import src.engine.ChessPieces.Knight;
import src.engine.ChessPieces.Pawn;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

// Moves chess engine 
// Queen, bishop, rook -> MOZI
// king, pawns, knight -> HMN
// adding the feature castling, capture and promotion and checkmate -> HMN

public class Moves {
    private BitBoard bitBoard;
    Pawn pawn = new Pawn();
    King king = new King();
    Knight knight = new Knight();

    public Moves(BitBoard bitBoard) {
        this.bitBoard = bitBoard;
    }

    public long makeMove(long from, long to, int playerColor) {
        if(playerColor == 0){
            return normal(from, to, PlayerColor.WHITE);
        }
        else{
            return normal(from, to, PlayerColor.BLACK);
        }
    }
    
    public long normal(long from, long to, PlayerColor playerColor){
        PiecesType piecesType = bitBoard.getPieceType(from);
        System.out.println(piecesType);
        long unoccupied = bitBoard.getUnOCc(to);
        long possible_move = getPossibleMoves(piecesType, playerColor,from, unoccupied);
        printBoardWithMoves(possible_move);
    
        long get_board = getBoard(piecesType, playerColor);
        System.out.println(get_board);
        if ((to & possible_move) != 0) {
            get_board &= ~from;
            get_board |= to;
            setBoard(piecesType, playerColor, get_board);
            return get_board;
        }
        System.out.println("Failed");
        return get_board;

    }
    public long PawnMove(long from, long to, long occ, long wPawn) {
        long possible_moves = pawn.white_possible_moves(from, BitBoard.empty);

        printBoardWithMoves(possible_moves);
        if ((to & possible_moves) != 0) {
            wPawn &= ~from;
            wPawn |= to;
            System.out.println("Worked");
            return wPawn;
        }

        System.out.println("Failed");
        return wPawn;
    }

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

        System.out.println("Board with Possible Moves:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public long getBoard(PiecesType piecesType, PlayerColor playerColor) {
        if (playerColor == PlayerColor.WHITE) {
            switch (piecesType) {
                case KING:
                    return bitBoard.getWKing();

                case PAWN:
                    return bitBoard.getWPawn();

                case KNIGHT:
                    return bitBoard.getWKnight();

                case BISHOP:
                    return bitBoard.getWBishop();

                case ROOK:
                    return bitBoard.getWRook();

                case QUEEN:
                    return bitBoard.getWQueen();

                default:
                    return 0;
            }
        } else {
            switch (piecesType) {
                case KING:
                    return bitBoard.getBKing();

                case PAWN:
                    return bitBoard.getBPawn();

                case KNIGHT:
                    return bitBoard.getBKnight();

                case BISHOP:
                    return bitBoard.getBBishop();

                case ROOK:
                    return bitBoard.getBRook();

                case QUEEN:
                    return bitBoard.getBQueen();

                default:
                    return 0;
            }
        }
    }

    public void setBoard(PiecesType piecesType, PlayerColor playerColor, long board) {
        if (playerColor == PlayerColor.WHITE) {
            switch (piecesType) {
                case KING:
                    bitBoard.setWKing(board);
                    break;

                case PAWN:
                    bitBoard.setWPawn(board);
                    break;

                case KNIGHT:
                    bitBoard.setWKnight(board);
                    break;

                case BISHOP:
                    bitBoard.setWBishop(board);
                    break;

                case ROOK:
                    bitBoard.setWRook(board);
                    break;

                case QUEEN:
                    bitBoard.setWQueen(board);
                    break;

                default:
                    return;
            }
        } else {
            switch (piecesType) {
                case KING:
                    bitBoard.setBKing(board);
                    break;

                case PAWN:
                    bitBoard.setBPawn(board);
                    break;

                case KNIGHT:
                    bitBoard.setBKnight(board);
                    break;

                case BISHOP:
                    bitBoard.setBBishop(board);
                    break;

                case ROOK:
                    bitBoard.setBRook(board);
                    break;

                case QUEEN:
                    bitBoard.setBQueen(board);
                    break;

                default:
                    return;
            }
        }
    }

    public long getPossibleMoves(PiecesType piecesType, PlayerColor playerColor, long from, long empty) {
        if(playerColor == PlayerColor.WHITE){
            switch (piecesType) {
                case KNIGHT:
                    return knight.white_possible_moves(from, empty);
    
                case KING:
                    return king.white_possible_moves(from, empty);
    
                case PAWN:
                    return pawn.white_possible_moves(from, empty);
    
                // case BISHOP:
                //     return bishop.white_possible_moves(from, empty);
    
                // case ROOK:
                //     return rook.white_possible_moves(from, empty);
    
                // case QUEEN:
                //     return queen.white_possible_moves(from, empty);
    
                default:
                    return 0;
            }
        }
        else{
            switch (piecesType) {
                case KING:
                    return king.black_possible_moves(from, empty);
                
                case PAWN:
                    return pawn.black_possible_moves(from, empty);

                case KNIGHT:
                    return knight.black_possible_moves(from, empty);

                // case BISHOP:
                //     return bishop.black_possible_moves(from, empty);

                // case ROOK:
                //     return rook.black_possible_moves(from, empty);

                // case QUEEN:  
                //     return queen.black_possible_moves(from, empty);
            
                default:
                    return 0;
            }
        }
    }

    public static long south(long pos) {
        return pos >> 8;
    }

    public static long north(long pos) {
        return pos << 8;
    }

    public static long east(long pos) {
        return pos << 1 & BitBoard.FILE_A;
    }

    public static long north_east(long pos) {
        return (pos << 9) & BitBoard.FILE_A;
    }

    public static long south_east(long pos) {
        return (pos >> 7) & BitBoard.FILE_A;
    }

    public static long west(long pos) {
        return pos >> 1 & BitBoard.FILE_H;
    }

    public static long north_west(long pos) {
        return (pos << 7) & BitBoard.FILE_H;
    }

    public static long south_west(long pos) {
        return (pos >> 9) & BitBoard.FILE_H;
    }

    // Knight
    public static long kNorth_NEast(long pos) {
        return (pos << 17) & BitBoard.FILE_A;
    }

    public static long kNorth_EEast(long pos) {
        return (pos << 10) & BitBoard.FILE_AB;
    }

    public static long KNorth_WWest(long pos) {
        return (pos << 6) & BitBoard.FILE_GH;
    }

    public static long KNorth_NWest(long pos) {
        return (pos << 15) & BitBoard.FILE_H;
    }

    public static long kSouth_EEast(long pos) {
        return (pos >> 6) & BitBoard.FILE_AB;
    }

    public static long KSouth_SEast(long pos) {
        return (pos >> 15) & BitBoard.FILE_A;
    }

    public static long KSouth_SWest(long pos) {
        return (pos >> 17) & BitBoard.FILE_H;
    }

    public static long KSouth_WWest(long pos) {
        return (pos >> 10) & BitBoard.FILE_GH;
    }

}
