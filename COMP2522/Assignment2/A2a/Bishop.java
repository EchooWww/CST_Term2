import java.awt.*;

/**
 * The Bishop class represents a chess bishop piece with a specified color and name.
 */
public class Bishop extends ChessPiece {

    /**
     * Creates a new Bishop instance with the given color and name.
     *
     * @param color The color of the bishop.
     * @param name  The name of the bishop.
     */
    public Bishop(Color color, String name) {
        super(color, name);
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a bishop.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid diagonal move for a bishop, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is a valid diagonal move for a bishop
        int rowDistance = Math.abs(toRow - fromRow);
        int colDistance = Math.abs(toCol - fromCol);
        return rowDistance == colDistance;
    }
}
