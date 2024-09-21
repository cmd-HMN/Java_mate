package src.engine.Interfaces;

import src.engine.ChessPieces.Bishop;
import src.engine.ChessPieces.King;
import src.engine.ChessPieces.Knight;
import src.engine.ChessPieces.Pawn;
import src.engine.ChessPieces.Rook;
import src.engine.Type.PiecesType;
import src.engine.Type.PlayerColor;


// I have used this method to get rid of massive if else and switch case statements
public class MainInterface {
    Pawn pawn = new Pawn();
    King king = new King();
    Knight knight = new Knight();
    Bishop bishop = new Bishop();
    Rook rook = new Rook();


    // make an array 
    private final MoveFunction[][] moveFunctions = new MoveFunction[PlayerColor.values().length][PiecesType.values().length];
    private final AttackFunction[][] attackFunctions = new AttackFunction[PlayerColor.values().length][PiecesType.values().length];


    // initialization 
    public MainInterface() {
        moveFunctions[PlayerColor.WHITE.ordinal()][PiecesType.KNIGHT.ordinal()] = knight::white_possible_moves;
        moveFunctions[PlayerColor.WHITE.ordinal()][PiecesType.KING.ordinal()] = king::white_possible_moves;
        moveFunctions[PlayerColor.WHITE.ordinal()][PiecesType.PAWN.ordinal()] = pawn::white_possible_moves;
        moveFunctions[PlayerColor.WHITE.ordinal()][PiecesType.BISHOP.ordinal()] = bishop::white_possible_moves;
        moveFunctions[PlayerColor.WHITE.ordinal()][PiecesType.ROOK.ordinal()] = rook::white_possible_moves;

        
        moveFunctions[PlayerColor.BLACK.ordinal()][PiecesType.KING.ordinal()] = king::black_possible_moves;
        moveFunctions[PlayerColor.BLACK.ordinal()][PiecesType.PAWN.ordinal()] = pawn::black_possible_moves;
        moveFunctions[PlayerColor.BLACK.ordinal()][PiecesType.KNIGHT.ordinal()] = knight::black_possible_moves;
        moveFunctions[PlayerColor.BLACK.ordinal()][PiecesType.BISHOP.ordinal()] = bishop::black_possible_moves;
        moveFunctions[PlayerColor.BLACK.ordinal()][PiecesType.ROOK.ordinal()] = rook::black_possible_moves;
        
        
        attackFunctions[PlayerColor.BLACK.ordinal()][PiecesType.KING.ordinal()] = (from, to, board) -> king.black_possible_attack(from, to, board);
        attackFunctions[PlayerColor.BLACK.ordinal()][PiecesType.PAWN.ordinal()] = (from, to, board) -> pawn.black_possible_attack(from, to, board);
        attackFunctions[PlayerColor.BLACK.ordinal()][PiecesType.KNIGHT.ordinal()] = (from, to, board) -> knight.black_possible_attack(from, to, board);
        attackFunctions[PlayerColor.BLACK.ordinal()][PiecesType.BISHOP.ordinal()] = (from, to, board) -> bishop.black_possible_attack(from, to, board);
        attackFunctions[PlayerColor.BLACK.ordinal()][PiecesType.ROOK.ordinal()] = (from, to, board) -> rook.black_possible_attack(from, to, board);
        
        attackFunctions[PlayerColor.WHITE.ordinal()][PiecesType.KING.ordinal()] = (from, to, board) -> king.white_possible_attack(from, to, board);
        attackFunctions[PlayerColor.WHITE.ordinal()][PiecesType.PAWN.ordinal()] = (from, to, board) -> pawn.white_possible_attack(from, to, board);
        attackFunctions[PlayerColor.WHITE.ordinal()][PiecesType.KNIGHT.ordinal()] = (from, to, board) -> knight.white_possible_attack(from, to, board);
        attackFunctions[PlayerColor.WHITE.ordinal()][PiecesType.BISHOP.ordinal()] = (from, to, board) -> bishop.white_possible_attack(from, to, board);
        attackFunctions[PlayerColor.WHITE.ordinal()][PiecesType.ROOK.ordinal()] = (from, to, board) -> rook.white_possible_attack(from, to, board);
    }

    // get all the possible moves
    public long getPossibleMoves(PiecesType piecesType, PlayerColor playerColor, long from, long empty) {
        return moveFunctions[playerColor.ordinal()][piecesType.ordinal()].apply(from, empty);
    }

    // get all the possible attack
    public long getPossibleAttack(PiecesType piecesType, PlayerColor playerColor, long from, long to, long empty){
        return attackFunctions[playerColor.ordinal()][piecesType.ordinal()].apply(from, to, empty);
    }

}
