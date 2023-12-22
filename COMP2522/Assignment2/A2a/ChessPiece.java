import java.awt.*;

/**
 * The ChessPiece class represents a chess piece with a specified color and name.
 */
public abstract class ChessPiece {
    private Color color;
    private String name;

    /**
     * Creates a new ChessPiece instance with the given color and name.
     *
     * @param color The color of the chess piece.
     * @param name  The name of the chess piece.
     */
    public ChessPiece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    /**
     * Checks if a move from one chess square to another is a valid move for this chess piece.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is valid, false otherwise.
     */
    public abstract boolean isValidMove(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol);

    /**
     * Gets the name of the chess piece.
     *
     * @return The name of the chess piece.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the color of the chess piece.
     *
     * @return The color of the chess piece.
     */
    public Color getColor() {
        return this.color;
    }
}
