import java.awt.*;

/**
 * The Pawn class represents a chess pawn piece with a specified color and name.
 */
public class Pawn extends ChessPiece {

    // Instance variables to check if it is the first move of the pawn
    private boolean firstMove;
    /**
     * Creates a new Pawn instance with the given color and name.
     *
     * @param color The color of the pawn.
     */
    public Pawn(Color color) {
        super(color);
        this.name = "Pawn";
        firstMove = true;
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a pawn.
     *
     * @param boards The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a pawn, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex) {
        int forwardDirection = getColor() == Color.WHITE ? -1 : 1;

        // First move can be 1 or 2 squares forward
        if (firstMove && toRow - fromRow == 2 * forwardDirection && toCol == fromCol) {
            int boardDiff = Math.abs(toBoardIndex - fromBoardIndex);
            if (boardDiff == 0 || boardDiff == 2) {
                firstMove = false;
                return true;
            }
            return false;
        }

        // Subsequent moves can be 1 square forward
        if( (toRow - fromRow == forwardDirection) && (toCol == fromCol)
                && (Math.abs(toBoardIndex - fromBoardIndex) <2)) {
            firstMove = false;
            return true;
        }
        return false;
    }

}
