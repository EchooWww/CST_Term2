import javax.swing.text.Position;
import java.awt.*;

/**
 * The ChessPiece class represents a chess piece with a specified color and name.
 */
public abstract class ChessPiece {
    private Color color;
    String name;
    private Position position;
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
     * @param boards  The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @param boardIndex   The board index of the target square.
     * @return true if the move is valid, false otherwise.
     */
    public abstract boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int boardIndex);


    /**
     * Checks if the way is clear for a move from one chess square to another.
     *
     * @param boards  The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @param toBoardIndex   The board index of the target square.
     * @return true if the way is clear, false otherwise.
     */
    public boolean isWayClear(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex) {
        boolean isDiagonal= Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol);
        if (toBoardIndex == fromBoardIndex){
            if (fromCol == toCol) {
                int startRow = Math.min(fromRow, toRow);
                int endRow = Math.max(fromRow, toRow);
                for (int row = startRow + 1; row < endRow; row++) {
                    if (boards[fromBoardIndex].squares[row][fromCol].getPiece() != null) {
                        return false;
                    }
                }
            } else if (fromRow == toRow) {
                int startCol = Math.min(fromCol, toCol);
                int endCol = Math.max(fromCol, toCol);
                for (int col = startCol + 1; col < endCol; col++) {
                    if (boards[fromBoardIndex].squares[fromRow][col].getPiece() != null) {
                        return false;
                    }
                }
            } else if (isDiagonal) {
                int rowIncrement = (toRow > fromRow) ? 1 : -1;
                int colIncrement = (toCol > fromCol) ? 1 : -1;

                int row = fromRow + rowIncrement;
                int col = fromCol + colIncrement;

                while (row != toRow && col != toCol) {
                    if (boards[fromBoardIndex].squares[row][col].getPiece() != null) {
                        // There's a piece in the way
                        return false;
                    }
                    row += rowIncrement;
                    col += colIncrement;
                }
            }
            return true;
        } else if (Math.abs(toBoardIndex-fromBoardIndex) == 2) {
            if (isDiagonal) {
                int indexIncrement = (toBoardIndex > fromBoardIndex) ? 1 : -1;
                int rowIncrement = (toRow > fromRow) ? 1 : -1;
                int colIncrement = (toCol > fromCol) ? 1 : -1;

                int row = fromRow + rowIncrement;
                int col = fromCol + colIncrement;
                int index = fromBoardIndex + indexIncrement;

                while (row != toRow && col != toCol) {
                    if (boards[index].squares[row][col].getPiece() != null) {
                        // There's a piece in the way
                        return false;
                    }
                    index += indexIncrement;
                    row += rowIncrement;
                    col += colIncrement;
                }
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