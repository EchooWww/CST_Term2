import java.awt.*;

/**
 * The Pawn class represents a chess pawn piece with a specified color and name.
 */
public class Pawn extends ChessPiece {

    /**
     * Creates a new Pawn instance with the given color and name.
     *
     * @param color The color of the pawn.
     * @param name  The name of the pawn.
     */
    public Pawn(Color color, String name) {
        super(color, name);
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
        // Implement pawn's movement rules here
        return false;
    }
}
