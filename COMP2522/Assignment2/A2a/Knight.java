import java.awt.*;

/**
 * The Knight class represents a chess knight piece with a specified color and name.
 */
public class Knight extends ChessPiece {

    /**
     * Creates a new Knight instance with the given color and name.
     *
     * @param color The color of the knight.
     * @param name  The name of the knight.
     */
    public Knight(Color color, String name) {
        super(color, name);
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a knight.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a knight (L-shaped move), false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is a valid move for a knight (L-shaped move)
        int rowDistance = Math.abs(toRow - fromRow);
        int colDistance = Math.abs(toCol - fromCol);
        return (rowDistance == 2 && colDistance == 1) || (rowDistance == 1 && colDistance == 2);
    }
}
