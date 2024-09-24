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
    Valid valid = new Valid();
    AttackBoard attackBoard;

    // initialize the bitboard
    public FeaturedMoves(BitBoard bitBoard, MainInterface mainInterface) {
        this.bitBoard = bitBoard;
        this.mainInterface = mainInterface;
        this.attackBoard = new AttackBoard(bitBoard);

    }


    // make the move (universal)
    public void makeMove(long from, long to, int playerColor, int moveType) {
        if(moveType == 0){     
            if (playerColor == 0) {
                normal(from, to, PlayerColor.WHITE);
                bitBoard.printBoard();
            }
            if (playerColor == 1) {
                normal(from, to, PlayerColor.BLACK);
                bitBoard.printBoard();
            }
        }   
        if(moveType == 1){
            if (playerColor == 0) {
                capture(from, to, PlayerColor.WHITE);
                bitBoard.printBoard();
            }
            if (playerColor == 1) {
                capture(from, to, PlayerColor.BLACK);
                bitBoard.printBoard();
            }
        }
        if(moveType == 2){
            if (playerColor == 0) {
                enPassant(from, to, PlayerColor.WHITE);
                bitBoard.printBoard();
            }
            if (playerColor == 1) {
                enPassant(from, to, PlayerColor.BLACK);
                bitBoard.printBoard();
            }
        }
        if(moveType == 3){
            if (playerColor == 0) {
                promotion(from, to, PlayerColor.WHITE);
                bitBoard.printBoard();
            }
            if (playerColor == 1) {
                promotion(from, to, PlayerColor.BLACK);
                bitBoard.printBoard();
            }
        }
    }
    // make the normal move (only movement)
    public long normal(long from, long to, PlayerColor playerColor) {
        if(valid.isDoubleSquare(from, to, playerColor)){
            bitBoard.enPassantT = playerColor == PlayerColor.WHITE ?  (to >> 8) : (to << 8);
        }

        PiecesType piecesType = bitBoard.getPieceType(from);
    
        long unoccupied = bitBoard.getUnOcc(to);
        
        long possible_move = mainInterface.getPossibleMoves(piecesType, playerColor, from, unoccupied);

        long get_board = getBoard(piecesType, playerColor);

        if(from == Bits.H4 && to == Bits.H3){
            attackBoard.getAttackBoard(playerColor.getOppositeColor());
        }
        System.out.println((to & possible_move) != 0);
        if ((to & possible_move) != 0) {
            get_board &= ~from;
            get_board |= to;
            setBoard(piecesType, playerColor, get_board);
            return get_board;
        }
        System.out.println("Failed");
        return get_board;
    }


    // make the capture move
    public long capture(long from, long to, PlayerColor playerColor){
        System.out.println("Capture");
        PiecesType piecesType = bitBoard.getPieceType(from);


        PiecesType piecesType2 = bitBoard.getPieceType(to);
        long to_ = getBoard(piecesType2, playerColor.getOppositeColor());

        long unoccupied = bitBoard.getUnOcc(to);

        long possible_attack = mainInterface.getPossibleAttack(piecesType, playerColor, from, to_, unoccupied);

        long get_board = getBoard(piecesType, playerColor);
        if ((to & possible_attack) != 0) {
            get_board &= ~from;
            get_board |= to;
            to_ &= ~to;
            setBoard(piecesType2, playerColor.getOppositeColor(), to_);
            setBoard(piecesType, playerColor, get_board);
            return get_board;
        }
        System.out.println("Failed");
        return 0L;
    }

    public long castle(long from, long to, PlayerColor playerColor){    
        System.out.println("Castling");
        return 0L;
    }

    // en Passant(under development)
    public long enPassant(long from, long to, PlayerColor playerColor){
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
            return get_board;
        }

        return 0L;
    }

    //promotion
    public long promotion(long from, long to, PlayerColor playerColor){
        // get the pieceType before capture
        PiecesType piecesType_to = bitBoard.getPieceType(to);

        System.out.println("Promotion");
        capture(from, to, playerColor); 
        bitBoard.printBoard();
        
        boolean checkValidity = playerColor == PlayerColor.WHITE 
                            ? (to & BitBoard.RANK_8) != 0
                            : (to & BitBoard.RANK_1) != 0;

        System.out.println(checkValidity);
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
            return get_board;
        }
        else{
            System.out.println("Failed");
            return 0L;
        }
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
