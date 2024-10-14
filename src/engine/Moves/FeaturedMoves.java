package src.engine.Moves;

import src.engine.BitBoard;

import src.engine.Bits.Bits;

import src.engine.Interfaces.MainInterface;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;
import src.engine.Validity.Valid;

public class FeaturedMoves {
    private BitBoard bitBoard;
    private MainInterface mainInterface;
    Valid valid;
    MoveBoard move_board;

    // initialize the bitboard
    public FeaturedMoves(BitBoard bitBoard, MainInterface mainInterface) {
        this.bitBoard = bitBoard;
        this.mainInterface = mainInterface;
        this.move_board = new MoveBoard(bitBoard);
        this.valid = new Valid(bitBoard);
    }


    // make the move (universal)
    public boolean makeMove(long from, long to, int playerColor) {
        int moveType = bitBoard.getMoveType(from, to);
        
        if(moveType == 0){     
            if (playerColor == 0) {
                if(normal(from, to, PlayerColor.WHITE)){
                    bitBoard.printBoard();
                    return true;
                }
            }
            if (playerColor == 1) {
                if(normal(from, to, PlayerColor.BLACK)){
                    bitBoard.printBoard();
                    return true;
                }
            }
        }   
        if(moveType == 1){
            if (playerColor == 0) {
                if(capture(from, to, PlayerColor.WHITE)){
                    bitBoard.printBoard();
                    return true;
                };
            }
            if (playerColor == 1) {
                if(capture(from, to, PlayerColor.BLACK)){
                    bitBoard.printBoard();
                    return true;
                }
            }
        }
        if(moveType == 2){
            if (playerColor == 0) {
                if(enPassant(from, to, PlayerColor.WHITE)){
                    bitBoard.printBoard();
                    return true;
                }
            }
            if (playerColor == 1) {
                if(enPassant(from, to, PlayerColor.BLACK)){
                    bitBoard.printBoard();
                    return true;
                }
            }
        }
        if(moveType == 3){
            if (playerColor == 0) {
                if(promotion(from, to, PlayerColor.WHITE)){
                    bitBoard.printBoard();
                    return true;
                }
            }
            if (playerColor == 1) {
                if(promotion(from, to, PlayerColor.BLACK)){
                    bitBoard.printBoard();
                    return true;
                }
            }
        }

        return false;
    }
    // make the normal move (only movement)
    public boolean normal(long from, long to, PlayerColor playerColor) {

        System.out.println(valid.kingInCheck(playerColor));
        if(valid.kingInCheck(playerColor)){
            System.out.println("King is in check");
            preventCheckMove(playerColor);
        }
        if(valid.isDoubleSquare(from, to, playerColor)){
            bitBoard.enPassantT = playerColor == PlayerColor.WHITE ?  (to >> 8) : (to << 8);
        }

        PiecesType piecesType = bitBoard.getPieceType(from);
    
        long unoccupied = bitBoard.getUnOcc();
        
        long possible_move = mainInterface.getPossibleMoves(piecesType, playerColor, from, unoccupied);

        long get_board = getBoard(piecesType, playerColor);

        if(from == Bits.H4 && to == Bits.H3){
            move_board.getAttackBoard(playerColor);
        }
        System.out.println((to & possible_move) != 0);
        if ((to & possible_move) != 0) {
            get_board &= ~from;
            get_board |= to;
            setBoard(piecesType, playerColor, get_board);
            return true;
        }
        System.out.println("Failed");
        return false;
    }


    // make the capture move
    public boolean capture(long from, long to, PlayerColor playerColor){
        System.out.println("Capture");
        PiecesType piecesType = bitBoard.getPieceType(from);


        PiecesType piecesType2 = bitBoard.getPieceType(to);
        long to_ = getBoard(piecesType2, playerColor.getOppositeColor());
        System.out.println("Opposite Lines");

        long unoccupied = bitBoard.getUnOcc();

        long possible_attack = mainInterface.getPossibleAttack(piecesType, playerColor, from, to_, unoccupied);

        long get_board = getBoard(piecesType, playerColor);
        if ((to & possible_attack) != 0) {
            get_board &= ~from;
            get_board |= to;
            to_ &= ~to;
            setBoard(piecesType2, playerColor.getOppositeColor(), to_);
            setBoard(piecesType, playerColor, get_board);
            return true;
        }
        System.out.println("Failed");
        return false;
    }

    public long castle(long from, long to, PlayerColor playerColor){    
        System.out.println("Castling");
        return 0L;
    }

    // en Passant(under development)
    public boolean enPassant(long from, long to, PlayerColor playerColor){
        System.out.println("En Passant");

        if(to == bitBoard.enPassantT){
            long get_board = getBoard(PiecesType.PAWN, playerColor);
            get_board &= ~from;
            get_board |= to;
            setBoard(PiecesType.PAWN, playerColor, get_board);

            long opponentBoard = getBoard(PiecesType.PAWN, playerColor.getOppositeColor());
            long capturedPawnSquare = playerColor == PlayerColor.WHITE ? (to >>> 8) : (to << 8);
            opponentBoard &= ~capturedPawnSquare;
            setBoard(PiecesType.PAWN, playerColor.getOppositeColor(), opponentBoard);

            bitBoard.enPassantT = 0L;
            return true;
        }

        return false;
    }

    //promotion
    public boolean promotion(long from, long to, PlayerColor playerColor){
        // get the pieceType before capture
        PiecesType piecesType_to = bitBoard.getPieceType(to);
        
        System.out.println("Promotion");
        capture(from, to, playerColor); 
        
        boolean checkValidity = playerColor == PlayerColor.WHITE 
                            ? (to & BitBoard.RANK_8) != 0
                            : (to & BitBoard.RANK_1) != 0;

        if(checkValidity){

            //get the user requested bit board
            PiecesType piecesType = bitBoard.getPieceTypeFromString();
            long get_promoting_board = getBoard(piecesType, playerColor);
            get_promoting_board |= to;

            // the bit board of the pawn
            long get_board = getBoard(PiecesType.PAWN, playerColor);
            get_board &= ~to;

            // the bit board of the piece at the to position
            long to_ = getBoard(piecesType_to, playerColor.getOppositeColor());
            to_ &= ~to;


            System.out.println("Rook");
            printBoardWithMoves(to_);
            setBoard(piecesType_to, playerColor.getOppositeColor(), to_);
            setBoard(piecesType, playerColor, get_promoting_board);
            setBoard(PiecesType.PAWN, playerColor, get_board);
            return true;
        }
        else{
            System.out.println("Failed");
            return false;
        }
    }

    public boolean getPromotionDialog(){
        return false;
    }

    public long getAllMoves(long from, int temp_playerColor){
        PiecesType piecesType = bitBoard.getPieceType(from);
        
        PlayerColor playerColor = temp_playerColor == 0 ? PlayerColor.WHITE : PlayerColor.BLACK; 
        long enPassantMove = 0L;
        System.out.println("ENpassant Move");
        printBoardWithMoves(bitBoard.enPassantT);
        System.out.println();
        if(((bitBoard.enPassantT) != 0) && piecesType == PiecesType.PAWN){
            switch (temp_playerColor) {
                case 0:
                    if((((from << 7 & BitBoard.FILE_H) | (from << 9 & BitBoard.FILE_A)) & bitBoard.enPassantT) != 0){
                        enPassantMove = bitBoard.enPassantT;
                    }
                    break;

                case 1:
                    if(((((from >> 9) & BitBoard.FILE_A) | ((from >>> 7) & BitBoard.FILE_A)) & bitBoard.enPassantT) != 0){
                        enPassantMove = bitBoard.enPassantT;
                    }
                    break;
            
                default:
                    break;
            }
        }

        long get_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());
        long get_unOcc = bitBoard.getUnOcc();
        if(piecesType  == PiecesType.NONE){
            return 0L;
        }
        return mainInterface.getPossibilities(piecesType, playerColor, from, get_unOcc, get_board) | enPassantMove;
    }

    public boolean isWhiteTurn(long from){
        return bitBoard.getColor(from) == 1 ? true : false;
    }

    public long getAllPossibleMove(PlayerColor playerColor){
        System.out.println("Possible Moves!!");
        long possibleMoves = 0L;
        for(int i = 0; i < 64; i++){
            if((bitBoard.getOccSquaresByColor(playerColor) & (1L << i)) != 0){
                possibleMoves |= getAllMoves((1L << i), playerColor.getOppositeColor() == PlayerColor.WHITE ? 0: 1);
            }
        }

        printBoardWithMoves(possibleMoves);
        System.out.println();
        return possibleMoves;
    }


    public long preventCheckMove(PlayerColor playerColor){
        long prevention_board = 0L;
        long attack_board = move_board.getAttackBoard(playerColor.getOppositeColor());
        System.out.println("Attack Board");
        printBoardWithMoves(attack_board);

        long prevention = getAllPossibleMove(playerColor);
        System.out.println("Prevention");
        printBoardWithMoves(prevention);
        System.out.println();
        prevention_board = prevention & attack_board;

        System.out.println("Prevention board");
        printBoardWithMoves(prevention_board);
        return prevention_board;
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

    // get the board from the bitboard
    public long getBoard(PiecesType piecesType, PlayerColor playerColor) {
        return bitBoard.getBitBoard(piecesType, playerColor);
    }

    // set the board in the bitboard
    public void setBoard(PiecesType piecesType, PlayerColor playerColor, long board) {
        bitBoard.setBitBoard(piecesType, playerColor, board);
    }   
}
