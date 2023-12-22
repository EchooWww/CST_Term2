import javax.swing.*;
import java.awt.*;

/**
 * The ChessSquare class represents a square on a chessboard, providing interaction
 * for selecting and deselecting squares.
 */
public class ChessSquare extends JPanel {
    public static final int SQUARE_SIZE = 50;
    private static final Color lightColor = new Color(231, 231, 205); // The background color for light squares
    private static final Color darkColor = new Color(115, 146, 83); // The background color for dark squares
    private final int row;
    private final int col;
    private ChessPiece piece;
    private final boolean isLightSquare;
    private int boardIndex;

    /**
     * Creates a new ChessSquare instance with the specified background color.
     *
     * @param row          The row index of the square.
     * @param col          The column index of the square.
     * @param isLightSquare Indicates whether this square is a light square on the chessboard.
     * @param piece        The chess piece located on this square (can be null if no piece).
     */
    public ChessSquare(int row, int col, boolean isLightSquare, ChessPiece piece) {
        // Set the background color based on whether it is a light or dark square
        setBackground(isLightSquare ? lightColor : darkColor);
        this.row = row;
        this.col = col;
        this.piece = piece;
        this.isLightSquare = isLightSquare;
    }

    /**
     * Gets the chess piece located on this square.
     *
     * @return The chess piece on this square (can be null if no piece).
     */
    public ChessPiece getPiece() {
        return piece;
    }

    /**
     * Sets the chess piece located on this square.
     *
     * @param piece The chess piece to be placed on this square (can be null if no piece).
     */
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (piece != null) {
            // Display the piece name on the square
            String pieceName = piece.getName();
            g.setColor(piece.getColor()); // Set font color
            int x = (getWidth() - g.getFontMetrics().stringWidth(pieceName)) / 2;
            int y = (getHeight() + g.getFontMetrics().getHeight()) / 2;
            g.drawString(pieceName, x, y);
        }
    }

    /**
     * Selects the square, adding a border to indicate selection.
     */
    public void select() {
        setBorder(BorderFactory.createLineBorder(Color.lightGray, 3)); // Adjust the border color and thickness as needed
    }

    /**
     * Deselects the square, removing the border to indicate deselection.
     */
    public void deselect() {
        setBorder(null);
    }

    /**
     * Gets the row index of this square.
     *
     * @return The row index of this square.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of this square.
     *
     * @return The column index of this square.
     */

    public int getCol() {
        return col;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }


    public int getBoardIndex() {
        return this.boardIndex;
    }

}
