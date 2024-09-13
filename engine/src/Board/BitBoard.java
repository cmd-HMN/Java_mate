package engine.src.Board;

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
    public long getWhitePawns() {
        return whitePawns;
    }

    public long wGetKing(){
        return whiteKings;
    }

    public void setKing(long bit){
        this.whiteKings = bit;
    }

    public long getBlackPawns() {
        return blackPawns;
    }
    public void setWhitePawns(long whitePawns) {
        this.whitePawns = whitePawns;
    }

    public void setBishopMove(long whiteBishop){
        this.whiteBishops = whiteBishop;
    }

    public long wGetKnight(){
        return whiteKnights;
    }

    public void setKnights(long knight){
        this.whiteKnights = knight;
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
