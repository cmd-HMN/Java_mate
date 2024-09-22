package src.engine;

import java.util.Scanner;

import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

public class BitBoard {

    // temp board
    private long board;

    // array used to handle the board
    private long[][] bitboards = new long[2][6];

    public long enPassantT = 0L;

    // bitboards
    private long whitePawns = 0x000000000000FF00L;
    private long whiteKnights = 0x0000000000000042L;
    private long whiteBishops = 0x0000000000000024L;
    private long whiteRooks = 0x0000000000000081L;
    private long whiteQueens = 0x0000000000000008L;
    private long whiteKings = 0x0000000000000010L;

    private long blackPawns = 0x00FF000000000000L;
    private long blackKnights = 0x4200000000000000L;
    private long blackBishops = 0x2400000000000000L;
    private long blackRooks = 0x8100000000000000L;
    private long blackQueens = 0x0800000000000000L;
    private long blackKings = 0x1000000000000000L;

    // file and rank masks
    public static final long FILE_A = 0xFEFEFEFEFEFEFEFEL;
    public static final long FILE_H = 0x7F7F7F7F7F7F7F7FL;
    public static final long FILE_AB = 0xFCFCFCFCFCFCFCFCL;
    public static final long FILE_GH = 0x3F3F3F3F3F3F3F3FL;

    // some rank used in the development
    public static final long RANK_1 = 0x00000000000000FFL;
    public static final long RANK_2 = 0x000000000000FF00L;
    public static final long RANK_4 = 0x00000000FF000000L;
    public static final long RANK_5 = 0x000000FF00000000L;
    public static final long RANK_7 = 0x00FF000000000000L;
    public static final long RANK_8 = 0xFF00000000000000L;


    public static final long DIAGONAL_MASKS[] = //from top left to bottom right
          { 0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L, 0x102040810204080L,
                  0x204081020408000L, 0x408102040800000L, 0x810204080000000L, 0x1020408000000000L,
                  0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L };
                  
  public static final long ANTIDIAGONAL_MASKS[] = // from top right to bottom left
          { 0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
                  0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
                  0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L };

    // used to initialize the bitboard as it is called
    public BitBoard() {

        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.KING.ordinal()] = whiteKings; // White King
        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.PAWN.ordinal()] = whitePawns; // White Pawns
        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.KNIGHT.ordinal()] = whiteKnights; // White Knights
        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.BISHOP.ordinal()] = whiteBishops; // White Bishops
        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.ROOK.ordinal()] = whiteRooks; // White Rooks
        bitboards[PlayerColor.WHITE.ordinal()][PiecesType.QUEEN.ordinal()] = whiteQueens; // White Queen

        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.KING.ordinal()] = blackKings; // Black King
        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.PAWN.ordinal()] = blackPawns; // Black Pawns
        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.KNIGHT.ordinal()] = blackKnights; // Black Knights
        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.BISHOP.ordinal()] = blackBishops; // Black Bishops
        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.ROOK.ordinal()] = blackRooks; // Black Rooks
        bitboards[PlayerColor.BLACK.ordinal()][PiecesType.QUEEN.ordinal()] = blackQueens; // Black Queen
    }

    // get the respective bitboard
    public long getBitBoard(PiecesType piecesType, PlayerColor playerColor) {
        return bitboards[playerColor.ordinal()][piecesType.ordinal()];
    }

    // set the respective bitboard
    public void setBitBoard(PiecesType piecesType, PlayerColor playerColor, long board) {
        if (playerColor == PlayerColor.WHITE) {
            switch (piecesType) {
                case PAWN:
                    whitePawns = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whitePawns; // Update bitboards array if
                                                                                         // needed
                    break;
                case KNIGHT:
                    whiteKnights = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteKnights; // Update bitboards array if
                                                                                           // needed
                    break;
                case BISHOP:
                    whiteBishops = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteBishops; // Update bitboards array if
                                                                                           // needed
                    break;
                case ROOK:
                    whiteRooks = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteRooks; // Update bitboards array if
                                                                                         // needed
                    break;
                case QUEEN:
                    whiteQueens = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteQueens; // Update bitboards array if
                                                                                          // needed
                    break;
                case KING:
                    whiteKings = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteKings; // Update bitboards array if
                                                                                         // needed
                    break;
                default:
                    break;
            }
        } else {
            switch (piecesType) {
                case PAWN:
                    blackPawns = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackPawns; // Update bitboards array if
                                                                                         // needed
                    break;
                case KNIGHT:
                    blackKnights = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackKnights; // Update bitboards array if
                                                                                           // needed
                    break;
                case BISHOP:
                    blackBishops = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackBishops; // Update bitboards array if
                                                                                           // needed
                    break;
                case ROOK:
                    blackRooks = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackRooks; // Update bitboards array if
                                                                                         // needed
                    break;
                case QUEEN:
                    blackQueens = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackQueens; // Update bitboards array if
                                                                                          // needed
                    break;
                case KING:
                    blackKings = board;
                    bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackKings; // Update bitboards array if
                                                                                         // needed
                    break;
                default:
                    break;
            }
        }
    }

    // get the piece type using pos
    public PiecesType getPieceType(long pos) {

        if ((whitePawns & pos) != 0)
            return PiecesType.PAWN;
        else if ((whiteKnights & pos) != 0)
            return PiecesType.KNIGHT;
        else if ((whiteBishops & pos) != 0)
            return PiecesType.BISHOP;
        else if ((whiteRooks & pos) != 0)
            return PiecesType.ROOK;
        else if ((whiteQueens & pos) != 0)
            return PiecesType.QUEEN;
        else if ((whiteKings & pos) != 0)
            return PiecesType.KING;

        else if ((blackPawns & pos) != 0)
            return PiecesType.PAWN;
        else if ((blackKnights & pos) != 0)
            return PiecesType.KNIGHT;
        else if ((blackBishops & pos) != 0)
            return PiecesType.BISHOP;
        else if ((blackRooks & pos) != 0)
            return PiecesType.ROOK;
        else if ((blackQueens & pos) != 0)
            return PiecesType.QUEEN;
        else if ((blackKings & pos) != 0)
            return PiecesType.KING;

        else {
            return PiecesType.NONE;
        }
    }

    // get all the occ squares
    public long getOcc(long to) {
        return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKings |
                blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKings;
    }

    // get all the unoccupied squares
    public long getUnOcc(long to) {
        long board_full = 0xFFFFFFFFFFFFFFFFL;
        long occ = getOcc(to);

        return board_full & ~occ;
    }

    // get all the occ squares by color
    public long getOccSquaresByColor(PlayerColor playerColor) {
        System.out.println("Working");
        switch (playerColor) {
            case WHITE:
                return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKings;
            case BLACK:
                return blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKings;

            default:
                return 0L;
        }
    }

    // get the piece type as string (haven't used)
    public String getPieceTypeAsString(long pos) {
        PiecesType pieceType = getPieceType(pos);

        switch (pieceType) {
            case PAWN:
                return "Pawn";
            case KNIGHT:
                return "Knight";
            case BISHOP:
                return "Bishop";
            case ROOK:
                return "Rook";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            case NONE:
                return "None";
            default:
                return "Unknown";
        }
    }

    // get the pieceType using the string given by the use
    public PiecesType getPieceTypeFromString() {
        Scanner scanner = new Scanner(System.in);
        String pieceType = scanner.nextLine();
        scanner.close();

        switch (pieceType) {
            case "Pawn":
                return PiecesType.PAWN;
            case "Knight":
                return PiecesType.KNIGHT;
            case "Bishop":
                return PiecesType.BISHOP;
            case "Rook":
                return PiecesType.ROOK;
            case "Queen":
                return PiecesType.QUEEN;
            default:
                return PiecesType.NONE;
        }
    }

    // print the board
    public void printBoard() {

        StringBuilder boardStringRepresentation = new StringBuilder();

        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                int pos = rank * 8 + file;
                char piece = '-';

                if (((whitePawns >> pos) & 1L) != 0)
                    piece = 'P';
                else if (((whiteKnights >> pos) & 1L) != 0)
                    piece = 'N';
                else if (((whiteBishops >> pos) & 1L) != 0)
                    piece = 'B';
                else if (((whiteRooks >> pos) & 1L) != 0)
                    piece = 'R';
                else if (((whiteQueens >> pos) & 1L) != 0)
                    piece = 'Q';
                else if (((whiteKings >> pos) & 1L) != 0)
                    piece = 'K';

                else if (((blackPawns >> pos) & 1L) != 0)
                    piece = 'p';
                else if (((blackKnights >> pos) & 1L) != 0)
                    piece = 'n';
                else if (((blackBishops >> pos) & 1L) != 0)
                    piece = 'b';
                else if (((blackRooks >> pos) & 1L) != 0)
                    piece = 'r';
                else if (((blackQueens >> pos) & 1L) != 0)
                    piece = 'q';
                else if (((blackKings >> pos) & 1L) != 0)
                    piece = 'k';

                boardStringRepresentation.append(piece).append(' ');
            }
            boardStringRepresentation.append('\n');
        }
        System.out.println(boardStringRepresentation.toString());
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
