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
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a rook, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        // Check for horizontal movement
        if (fromRow == toRow || fromCol == toCol) {
            return isWayClear(board, fromRow, fromCol, toRow, toCol);
        }
        // Invalid move for a Rook
        return false;
    }
}
