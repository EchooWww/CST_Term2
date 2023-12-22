import java.awt.*;

/**
 * The King class represents a chess king piece with a specified color and name.
 */
public class King extends ChessPiece {

    /**
     * Creates a new King instance with the given color and name.
     *
     * @param color The color of the king.
     * @param name  The name of the king.
     */
    public King(Color color, String name) {
        super(color, name);
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a king.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a king (one square in any direction), false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is a valid move for a king (one square in any direction)
        int rowDistance = Math.abs(toRow - fromRow);
        int colDistance = Math.abs(toCol - fromCol);
        return (rowDistance == 1 && colDistance == 0) || (rowDistance == 0 && colDistance == 1) || (rowDistance == 1 && colDistance == 1);
    }
}
