package src.engine;


import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;

public class BitBoard {
    private long board;
    private long[][] bitboards = new long[2][6];

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

    public static final long FILE_A = 0xFEFEFEFEFEFEFEFEL;
    public static final long FILE_H = 0x7F7F7F7F7F7F7F7FL;
    public static final long FILE_AB = 0xFCFCFCFCFCFCFCFCL;
    public static final long FILE_GH = 0x3F3F3F3F3F3F3F3FL;

    public static final long RANK_2 = 0x000000000000FF00L;
    public static final long RANK_4 = 0x00000000FF000000L;
    public static final long RANK_5 = 0x000000FF00000000L;
    public static final long RANK_7 = 0x00FF000000000000L;


    public static final long empty = ~0L;

    public BitBoard() {

    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.KING.ordinal()] = whiteKings;   // White King
    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.PAWN.ordinal()] = whitePawns;   // White Pawns
    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.KNIGHT.ordinal()] = whiteKnights; // White Knights
    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.BISHOP.ordinal()] = whiteBishops; // White Bishops
    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.ROOK.ordinal()] = whiteRooks;   // White Rooks
    bitboards[PlayerColor.WHITE.ordinal()][PiecesType.QUEEN.ordinal()] = whiteQueens;  // White Queen


    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.KING.ordinal()] = blackKings;   // Black King
    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.PAWN.ordinal()] = blackPawns;   // Black Pawns
    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.KNIGHT.ordinal()] = blackKnights; // Black Knights
    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.BISHOP.ordinal()] = blackBishops; // Black Bishops
    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.ROOK.ordinal()] = blackRooks;   // Black Rooks
    bitboards[PlayerColor.BLACK.ordinal()][PiecesType.QUEEN.ordinal()] = blackQueens;  // Black Queen
}

    public long getBitBoard(PiecesType piecesType, PlayerColor playerColor) {
        return bitboards[playerColor.ordinal()][piecesType.ordinal()];
    }

    public void setBitBoard(PiecesType piecesType, PlayerColor playerColor, long board) {
        if(playerColor == PlayerColor.WHITE){
            switch (piecesType){
                case PAWN:
                whitePawns = board;
                bitboards[playerColor.ordinal()][piecesType.ordinal()] = whitePawns; // Update bitboards array if needed
                break;
                case KNIGHT:
                whiteKnights = board;
                bitboards[playerColor.ordinal()][piecesType.ordinal()] = whiteKnights; // Update bitboards array if needed
                break;
            }
        }
        else{
            switch (piecesType){
                case PAWN:
                blackPawns = board;
                bitboards[playerColor.ordinal()][piecesType.ordinal()] = blackPawns; // Update bitboards array if needed
                break;
            }
        }
    }

    public PiecesType getPieceType(long pos) {
    
        if ((whitePawns & pos) != 0) return PiecesType.PAWN;
        else if ((whiteKnights & pos) != 0) return PiecesType.KNIGHT;
        else if ((whiteBishops & pos) != 0) return PiecesType.BISHOP;
        else if ((whiteRooks & pos) != 0) return PiecesType.ROOK;
        else if ((whiteQueens & pos) != 0) return PiecesType.QUEEN;
        else if ((whiteKings & pos) != 0) return PiecesType.KING;
    
        else if ((blackPawns & pos) != 0) return PiecesType.PAWN;
        else if ((blackKnights & pos) != 0) return PiecesType.KNIGHT;
        else if ((blackBishops & pos) != 0) return PiecesType.BISHOP;
        else if ((blackRooks & pos) != 0) return PiecesType.ROOK;
        else if ((blackQueens & pos) != 0) return PiecesType.QUEEN;
        else if ((blackKings & pos) != 0) return PiecesType.KING;
        
        else{
            return PiecesType.NONE;
        }
    }

    public long getOcc(long to){
        return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKings|
        blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKings;
    }

    public long getUnOcc(long to){
        long board_full = 0xFFFFFFFFFFFFFFFFL;
        long occ = getOcc(to);

        return board_full & ~occ;
    }

    public long getOccSquaresByColor(PlayerColor playerColor){
        switch (playerColor) {
            case WHITE:
                return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKings; 
            case BLACK:
                return blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKings;
    
            default:
                return 0L;
        }
    }
    public long getWOcc(){
        return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKings;
    }

    public long getBOcc(){
        return blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKings;
    }
    
    public String  getPieceTypeAsString(long pos) {
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

    public String printWhitePawns() {
        return toBinaryString(whitePawns);
    }

    private String toBinaryString(long value) {
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }

    public void sample() {

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                int pos = i * 8 + j;

                if ((board & (1L << pos)) != 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
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
}