import java.awt.*;

/**
 * The King class represents a chess king piece with a specified color and name.
 */
public class King extends ChessPiece {
    /**
     * Creates a new King instance with the given color and name.
     *
     * @param color The color of the king.
     */
    public King(Color color) {
        super(color);
        this.name = "King";
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a king.
     *
     * @param boards The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a king (one square in any direction), false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex){
        // Check if the move is a valid move for a king (one square in any direction)
        int rowDistance = Math.abs(toRow - fromRow);
        int colDistance = Math.abs(toCol - fromCol);
        int boardDistance = Math.abs(toBoardIndex - fromBoardIndex);
        return (boardDistance < 2 && (rowDistance == 1 && colDistance == 0) || (rowDistance == 0 && colDistance == 1) || (rowDistance == 1 && colDistance == 1));
    }
}
