import java.awt.*;

/**
 * The Rook class represents a chess rook piece with a specified color and name.
 */
public class Rook extends ChessPiece {
    /**
     * Creates a new Rook instance with the given color and name.
     *
     * @param color The color of the rook.
     */
    public Rook(Color color) {
        super(color);
        this.name = "Rook";
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a rook.
     *
     * @param boards   The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a rook, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex) {
        // Check for horizontal movement
        if ((fromRow == toRow && Math.abs(fromCol - toCol) == Math.abs(fromBoardIndex - toBoardIndex))
                || (fromCol == toCol && Math.abs(fromRow - toRow) == Math.abs(fromBoardIndex - toBoardIndex))) {
            return isWayClear(boards, fromRow, fromCol, fromBoardIndex, toRow, toCol, toBoardIndex);
        }
        // Invalid move for a Rook
        return false;
    }
}
