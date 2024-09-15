package src.engine;

import src.engine.Type.PiecesType;

public class BitBoard {
    private long board;

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

    public static final long empty = ~0L;

    public BitBoard() {
        board = whiteBishops | whiteKings | whiteKnights | whitePawns | whiteQueens | whiteRooks |
                blackBishops | blackKings | blackKnights | blackPawns | blackQueens | blackRooks;
    }

    public BitBoard(long board){
        this.board = board;
    }

    public long getWPawn(){
        return whitePawns;
    }

    public long getWKnight(){
        return whiteKnights;
    }

    public long getWBishop(){
        return whiteBishops;
    }

    public long getWRook(){
        return whiteRooks;
    }

    public long getWQueen(){
        return whiteQueens;
    }

    public long getWKing(){
        return whiteKings;
    }

    public long getBPawn(){
        return blackPawns;
    }

    public long getBKnight(){
        return blackKnights;
    }

    public long getBBishop(){
        return blackBishops;
    }

    public long getBRook(){
        return blackRooks;
    }

    public long getBQueen(){
        return blackQueens;
    }

    public long getBKing(){
        return blackKings;
    }

    public void setWPawn(long wPawn){
        this.whitePawns = wPawn;
    }

    public void setWKnight(long wKnight){
        this.whiteKnights = wKnight;
    }

    public void setWBishop(long wBishop){
        this.whiteBishops = wBishop;
    }
    
    public void setWRook(long wRook){
        this.whiteRooks = wRook;
    }

    public void setWQueen(long wQueen){
        this.whiteQueens = wQueen;
    }

    public void setWKing(long wKing){
        this.whiteKings = wKing;
    }

    public void setBPawn(long bPawn){
        this.blackPawns = bPawn;
    }

    public void setBKnight(long bKnight){
        this.blackKnights = bKnight;
    }

    public void setBBishop(long bBishop){
        this.blackBishops = bBishop;
    }

    public void setBRook(long bRook){
        this.blackRooks = bRook;
    }

    public void setBQueen(long bQueen){
        this.blackQueens = bQueen;
    }

    public void setBKing(long bKing){
        this.blackKings = bKing;
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
        return to & board;
    }

    public long getUnOCc(long to){
        long board_full = 0xFFFFFFFFFFFFFFFFL;
        long occ = getOcc(to);

        return board_full & ~occ;
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
}
