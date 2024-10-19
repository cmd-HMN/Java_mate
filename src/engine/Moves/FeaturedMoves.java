package src.engine.Moves;

import src.engine.BitBoard;


import src.engine.Interfaces.MainInterface;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;
import src.engine.Validity.Valid;

public class FeaturedMoves {
    private BitBoard bitBoard;
    private MainInterface mainInterface;
    Valid valid;
    AttackBoard attack_board;

    // initialize the bitboard
    public FeaturedMoves(BitBoard bitBoard, MainInterface mainInterface) {
        this.bitBoard = bitBoard;
        this.mainInterface = mainInterface;
        this.attack_board = new AttackBoard(bitBoard);
        this.valid = new Valid(bitBoard, this);
    }


    // make the move (universal)
    public boolean makeMove(long from, long to, int playerColor) {
        int moveType = bitBoard.getMoveType(from, to);
        valid.checkmate(playerColor == 0 ? PlayerColor.WHITE : PlayerColor.BLACK);
    
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
        if(moveType == 4){
            if (playerColor == 0) {
                if(castle(from, to, PlayerColor.WHITE)){
                    bitBoard.printBoard();
                    return true;
                }
            }
            if (playerColor == 1) {
                if(castle(from, to, PlayerColor.BLACK)){
                    bitBoard.printBoard();
                    return true;
                }
            }
        }

        return false;
    }
    // make the normal move (only movement)
    public boolean normal(long from, long to, PlayerColor playerColor) {

        System.out.println("Normal");
        if(valid.isDoubleSquare(from, to, playerColor) && bitBoard.enPassantT == 0L){
            bitBoard.enPassantT = playerColor == PlayerColor.WHITE ?  (to >> 8) : (to << 8);
        }else{
            bitBoard.enPassantT = 0L;
        }

        PiecesType piecesType = bitBoard.getPieceType(from);
    
        long unoccupied = bitBoard.getUnOcc();
        
        long possible_move = mainInterface.getPossibleMoves(piecesType, playerColor, from, unoccupied);

        long get_board = getBoard(piecesType, playerColor);

        System.out.println((to & possible_move) != 0);
        
        if (((to & possible_move) != 0)) {
            get_board &= ~from;
            get_board |= to;
            setBoard(piecesType, playerColor, get_board);
            if(valid.kingInCheck(playerColor)){
                get_board &= ~to;
                get_board |= from;
                setBoard(piecesType, playerColor, get_board);
                return false;
            }
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

        long unoccupied = bitBoard.getUnOcc();

        long possible_attack = mainInterface.getPossibleAttack(piecesType, playerColor, from, to_, unoccupied);
        printBoardWithMoves(possible_attack);

        long get_board = getBoard(piecesType, playerColor);
        if ((to & possible_attack) != 0) {
            get_board &= ~from;
            get_board |= to;
            to_ &= ~to;
            setBoard(piecesType2, playerColor.getOppositeColor(), to_);
            setBoard(piecesType, playerColor, get_board);
            if(valid.kingInCheck(playerColor)){
                get_board &= ~to;
                get_board |= from;
                to_ |= to;
                setBoard(piecesType2, playerColor.getOppositeColor(), to_);
                setBoard(piecesType, playerColor, get_board);
                return false;
            }
            bitBoard.enPassantT = 0L;
            return true;
        }
        System.out.println("Failed");
        return false;
    }

    public boolean castle(long from, long to, PlayerColor playerColor){    
        System.out.println("Castling");

        if(valid.kingInCheck(playerColor)){
            return false;
        }

        long get_attack_board = attack_board.getAttackBoard(playerColor.getOppositeColor());
        long get_occ = bitBoard.getOcc();
        long get_board_king = bitBoard.getBitBoard(PiecesType.KING, playerColor);
        long get_board_rook = bitBoard.getBitBoard(PiecesType.ROOK, playerColor);

        boolean isKingside = false;
        boolean isQueenSide = false;
        boolean kingSafe = false;
        boolean rookSafe = false;
        boolean castleConditionKingSide = ((get_occ & 0x0000000000000060L) == 0 && (get_attack_board & 0x0000000000000060L) == 0) || (get_occ & 0x6000000000000000L) == 0 && (get_attack_board & 0x6000000000000000L) == 0;
        boolean castleConditionQueenSide = ((get_occ & 0x000000000000000EL) == 0 && (get_attack_board & 0x000000000000000EL) == 0) || (get_occ & 0x0E00000000000000L) == 0 && (get_attack_board & 0x0E00000000000000L) == 0;

        if (playerColor == PlayerColor.WHITE) {
            isKingside = (from == (1L << 4) && to == (1L << 7));  // e1 to g1
            isQueenSide = (from == (1L << 4) && to == (1L << 0)); // e1 to c1
            kingSafe = (get_board_king & (1L << 4)) != 0;
            rookSafe = (get_board_rook & (1L << 0)) != 0 || (get_board_rook & (1L << 7)) != 0;
        } else if (playerColor == PlayerColor.BLACK) {
            isKingside = (from == (1L << 60) && to == (1L << 63));  // e8 to g8
            isQueenSide = (from == (1L << 60) && to == (1L << 56)); // e8 to c8
            kingSafe = (get_board_king & (1L << 60)) != 0;
            rookSafe = (get_board_rook & (1L << 56)) != 0 || (get_board_rook & (1L << 63)) != 0;
        }

        if(isKingside && kingSafe && rookSafe && castleConditionKingSide){
            get_board_king &= ~from;
            get_board_rook &= ~to;

            if(playerColor == PlayerColor.WHITE){
                get_board_king |= (1L << 6);
                get_board_rook |= (1L << 5);
            }

            if(playerColor == PlayerColor.BLACK){
                get_board_king |= (1L << 62);
                get_board_rook |= (1L << 61);
            }

            setBoard(PiecesType.KING, playerColor, get_board_king);
            setBoard(PiecesType.ROOK, playerColor, get_board_rook);

            bitBoard.enPassantT = 0L;
            return true;
        }
        else if((isQueenSide && kingSafe && rookSafe && castleConditionQueenSide)){
            System.out.println("Queen side entered");
            get_board_king &= ~from;
            get_board_rook &= ~to;

            if(playerColor == PlayerColor.WHITE){
                get_board_king |= (1L << 2);
                get_board_rook |= (1L << 3);
            }

            if(playerColor == PlayerColor.BLACK){
                get_board_king |= (1L << 58);
                get_board_rook |= (1L << 59);
            }

            setBoard(PiecesType.KING, playerColor, get_board_king);
            setBoard(PiecesType.ROOK, playerColor, get_board_rook);

            bitBoard.enPassantT = 0L;
            return true;
        }
        return false;
    }

    // en Passant
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

            if(valid.kingInCheck(playerColor)){
                get_board &= ~to;
                get_board |= from;
                setBoard(PiecesType.PAWN, playerColor, get_board);

                opponentBoard |= capturedPawnSquare;
                setBoard(PiecesType.PAWN, playerColor.getOppositeColor(), opponentBoard);
                return false;
            }

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
    
        boolean checkValidity = (playerColor == PlayerColor.WHITE 
                            ? (to & BitBoard.RANK_8) != 0
                            : (to & BitBoard.RANK_1) != 0) && 
                            playerColor == PlayerColor.WHITE ? (from & BitBoard.RANK_7) != 0 : (from  & BitBoard.RANK_2) != 05;

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
            setBoard(piecesType_to, playerColor.getOppositeColor(), to_);
            setBoard(piecesType, playerColor, get_promoting_board);
            setBoard(PiecesType.PAWN, playerColor, get_board);

            if(valid.kingInCheck(playerColor)){
                get_promoting_board &= ~to;
                get_board |= from;
                to_ |= to;
                setBoard(piecesType_to, playerColor.getOppositeColor(), to_);
                setBoard(piecesType, playerColor, get_promoting_board);
                setBoard(PiecesType.PAWN, playerColor, get_board);
                return false;
            }
            bitBoard.enPassantT = 0L;
            return true;
        }
        else{
            System.out.println("Failed");
            return false;
        }
    }

    public long getAllMoves(long from, int temp_playerColor){
        PiecesType piecesType = bitBoard.getPieceType(from);
        
        PlayerColor playerColor = temp_playerColor == 0 ? PlayerColor.WHITE : PlayerColor.BLACK; 
        long enPassantMove = 0L;
        //enPassant
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
        long castleMove = 0L;
        //castling
        if(((from & 1L << 4) != 0) || (from & 1L << 60) != 0)
        {
            System.out.println("castling");
            long get_attack_board = attack_board.getAttackBoard(playerColor.getOppositeColor());
            long get_occ = bitBoard.getOcc();
            long get_board_king = bitBoard.getBitBoard(PiecesType.KING, playerColor);
            long get_board_rook = bitBoard.getBitBoard(PiecesType.ROOK, playerColor);
    
            boolean kingSafe = false;
            boolean rookSafe = false;
            boolean castleConditionKingSide = ((get_occ & 0x0000000000000060L) == 0 && (get_attack_board & 0x0000000000000060L) == 0) || (get_occ & 0x6000000000000000L) == 0 && (get_attack_board & 0x6000000000000000L) == 0;
            boolean castleConditionQueenSide = ((get_occ & 0x000000000000000EL) == 0 && (get_attack_board & 0x000000000000000EL) == 0) || (get_occ & 0x0E00000000000000L) == 0 && (get_attack_board & 0x0E00000000000000L) == 0;
    
            if (playerColor == PlayerColor.WHITE) {
                kingSafe = (get_board_king & (1L << 4)) != 0;
                rookSafe = (get_board_rook & (1L << 0)) != 0 || (get_board_rook & (1L << 7)) != 0;
            } else if (playerColor == PlayerColor.BLACK) {
                kingSafe = (get_board_king & (1L << 60)) != 0;
                rookSafe = (get_board_rook & (1L << 56)) != 0 || (get_board_rook & (1L << 63)) != 0;
            }

            if(castleConditionKingSide && kingSafe && rookSafe){
                castleMove = (temp_playerColor == 0 ? 0x00000000000000E0L : 0xE000000000000000L) | castleMove;
            }
            if(castleConditionQueenSide && kingSafe && rookSafe){
                castleMove = (temp_playerColor == 0 ? 0x00000000000000F0L : 0x0F00000000000000L) | castleMove;
            }
        }

        //get the remaining thing
        long get_board = bitBoard.getOccSquaresByColor(playerColor.getOppositeColor());
        long get_unOcc = bitBoard.getUnOcc();
        if(piecesType  == PiecesType.NONE){
            return 0L;
        }
        return mainInterface.getPossibilities(piecesType, playerColor, from, get_unOcc, get_board) | enPassantMove | castleMove;
    }

    public boolean isWhiteTurn(long from){
        return bitBoard.getColor(from) == 1 ? true : false;
    }

    public long getAllPossibleMove(PlayerColor playerColor){
        System.out.println("Possible Moves!!");
        System.out.println(playerColor);
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
