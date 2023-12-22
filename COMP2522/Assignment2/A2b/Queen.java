import java.awt.*;

/**
 * The Queen class represents a chess queen piece with a specified color and name.
 */
public class Queen extends ChessPiece {

    /**
     * Creates a new Queen instance with the given color and name.
     *
     * @param color The color of the queen.
     */
    public Queen(Color color) {
        super(color);
        this.name = "Queen";
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a queen.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a queen, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol) || fromRow == toRow || fromCol == toCol) {
            // The queen is moving diagonally
            return isWayClear(board, fromRow, fromCol, toRow, toCol);
        }
        return false;
    }
}
