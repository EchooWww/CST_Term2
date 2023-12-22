import java.awt.*;

/**
 * The Bishop class represents a chess bishop piece with a specified color and name.
 */
public class Bishop extends ChessPiece {

    /**
     * Creates a new Bishop instance with the given color and name.
     *
     * @param color The color of the bishop.
     */
    public Bishop(Color color) {
        super(color);
        this.name = "Bishop";
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a bishop.
     *
     * @param boards  The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid diagonal move for a bishop, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex) {
        // Check for diagonal movement
        if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol) &&
                Math.abs(toRow - fromRow) == Math.abs(toBoardIndex - fromBoardIndex)){
            return isWayClear(boards, fromRow, fromCol, fromBoardIndex, toRow, toCol, toBoardIndex);
        }
        // Invalid move for a Bishop: not diagonal
        return false;
    }
}
