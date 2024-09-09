package engine.src.ChessBoard;

public class BitBoard {
    private long board;

    public BitBoard(){
        this.board = 0L;
    }

    public void setPiece(int pos){
        board |= (1L << pos);
    }

    public void removePiece(int pos){
        board |= (1L >> pos);
    }

    public void printBoard(){
        for(int i = 0; i < 64; i++){
            if((board & (1L << i)) != 0){
                System.out.print("1 ");
            }else{
                System.out.print("0 ");
            }
            if((i+1) % 8 == 0){
                System.out.println();
            }
        }
        };
}
