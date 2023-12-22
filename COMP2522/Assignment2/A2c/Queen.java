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
     * @param boards The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a queen, false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[] boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        int boardDiff = Math.abs(toBoardIndex - fromBoardIndex);

        if (boardDiff > 2) {
            return false;
        }

        if (fromBoardIndex == toBoardIndex) {
            return (rowDiff == colDiff || fromRow == toRow || fromCol == toCol) && isWayClear(boards, fromRow, fromCol, fromBoardIndex, toRow, toCol, toBoardIndex);
        }

        return (rowDiff == colDiff && rowDiff == boardDiff) ||
                (fromRow == toRow && colDiff == boardDiff) ||
                (fromCol == toCol && rowDiff == boardDiff)
                        && isWayClear(boards, fromRow, fromCol, fromBoardIndex, toRow, toCol, toBoardIndex);
    }
}
