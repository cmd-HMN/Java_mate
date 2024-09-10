package engine.src.Board;

public class BitBoard {
    private long board;

    private long whitePawns = 0x000000000000FF00L;
    private long whiteKnights= 0x0000000000000042L;
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
    

    public BitBoard(){
        board = 0x0000000000000000L;
    }
    public void setPiece(int pos){
        board |= (1L << pos);
    }

    public void removePiece(int pos){
        board &= ~(1L >> pos);
    }

    public void printBoard(){

        StringBuilder boardStringRepresentation = new StringBuilder();
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                int pos = rank * 8 + file;
                char piece = '-';
                
                if(((whitePawns >> pos) & 1L) != 0) piece = 'P';
                else if(((whiteKnights >> pos) & 1L) != 0) piece = 'N';
                else if(((whiteBishops >> pos) & 1L) != 0) piece = 'B';
                else if(((whiteRooks >> pos) & 1L) != 0) piece = 'R';
                else if(((whiteQueens >> pos) & 1L) != 0) piece = 'Q';
                else if(((whiteKings >> pos) & 1L) != 0) piece = 'K';

                else if(((blackPawns >> pos) & 1L) != 0) piece = 'p';
                else if(((blackKnights >> pos) & 1L) != 0) piece = 'n';
                else if(((blackBishops >> pos) &1L) != 0) piece = 'b';
                else if(((blackRooks >> pos) & 1L) != 0) piece = 'r';
                else if(((blackQueens >> pos) & 1L) != 0) piece = 'q';
                else if(((blackKings >> pos) & 1L) != 0) piece = 'k';

                boardStringRepresentation.append(piece).append(' ');
            }
                boardStringRepresentation.append('\n');
        }
        System.out.println(boardStringRepresentation.toString());    
    }
    public void sample(){           
        long board = whiteBishops | whiteKings | whiteKnights | whitePawns | whiteQueens | whiteRooks |
                 blackBishops | blackKings | blackKnights | blackPawns | blackQueens | blackRooks;

 
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
