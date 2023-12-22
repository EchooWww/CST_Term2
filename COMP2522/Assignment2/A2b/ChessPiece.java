import java.awt.*;

/**
 * The ChessPiece class represents a chess piece with a specified color and name.
 */
public abstract class ChessPiece {
    private Color color;
    String name;
    /**
     * Creates a new ChessPiece instance with the given color and name.
     *
     * @param color The color of the chess piece.
     */
    public ChessPiece(Color color) {
        this.color = color;
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
     * Checks if the way is clear for a move from one chess square to another.
     *
     * @param board   The chessboard represented as an array of chess squares.
     * @param fromRow The row of the source square.
     * @param fromCol The column of the source square.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the way is clear, false otherwise.
     */
    public boolean isWayClear(ChessSquare[][] board, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromCol == toCol) {
            int startRow = Math.min(fromRow, toRow);
            int endRow = Math.max(fromRow, toRow);
            for (int row = startRow + 1; row < endRow; row++) {
                if (board[row][fromCol].getPiece() != null) {
                    // There's a piece in the way
                    return false;
                }
            }
        } else if (fromRow == toRow) {
            int startCol = Math.min(fromCol, toCol);
            int endCol = Math.max(fromCol, toCol);
            for (int col = startCol + 1; col < endCol; col++) {
                if (board[fromRow][col].getPiece() != null) {
                    // There's a piece in the way
                    return false;
                }
            }
        } else if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) {
            int rowIncrement = (toRow > fromRow) ? 1 : -1;
            int colIncrement = (toCol > fromCol) ? 1 : -1;

            int row = fromRow + rowIncrement;
            int col = fromCol + colIncrement;

            while (row != toRow && col != toCol) {
                if (board[row][col].getPiece() != null) {
                    // There's a piece in the way
                    return false;
                }
                row += rowIncrement;
                col += colIncrement;
            }
        }
        return true;
    }

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
