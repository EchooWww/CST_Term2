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
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a pawn, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        int forwardDirection = getColor() == Color.WHITE ? -1 : 1;
        // First move can be 1 or 2 squares forward
        if (firstMove && toRow - fromRow == 2 * forwardDirection && toCol == fromCol) {
            firstMove = false;
            return true;
        }

        // Subsequent moves can be 1 square forward
        return toRow - fromRow == forwardDirection && toCol == fromCol;
    }
}
