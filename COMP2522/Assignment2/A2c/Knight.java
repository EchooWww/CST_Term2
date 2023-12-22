import java.awt.*;

/**
 * The Knight class represents a chess knight piece with a specified color and name.
 */
public class Knight extends ChessPiece {

    /**
     * Creates a new Knight instance with the given color and name.
     *
     * @param color The color of the knight.
     */
    public Knight(Color color) {
        super(color);
        this.name = "Knight";
    }

    /**
     * Checks if a move from one chess square to another is a valid move for a knight.
     *
     * @param boards The boards represented as an array of chess boards.
     * @param toRow   The row of the target square.
     * @param toCol   The column of the target square.
     * @return true if the move is a valid move for a knight (L-shaped move), false otherwise.
     */
    @Override
    public boolean isValidMove(ChessBoard[]boards, int fromRow, int fromCol, int fromBoardIndex, int toRow, int toCol, int toBoardIndex){
        // Check if the move is a valid move for a knight (L-shaped move)
        int rowDistance = Math.abs(toRow - fromRow);
        int colDistance = Math.abs(toCol - fromCol);
        int boardDistance = Math.abs(toBoardIndex - fromBoardIndex);
        return (boardDistance < 2 && (rowDistance == 2 && colDistance == 1) || (rowDistance == 1 && colDistance == 2));
    }
}
