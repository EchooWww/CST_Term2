import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

/**
 * The ChessBoard class represents a graphical chess board with squares for chess pieces.
 */
public class ChessBoard extends Board {
    ChessSquare[][] squares;
    private int boardSize;
    private int boardIndex;

    /**
     * Creates a new ChessBoard instance with a grid of chess squares.
     *
     * @param boardSize The size of the chessboard (e.g., 8 for an 8x8 board).
     * @param boardIndex The index of the board (0, 1, or 2).
     */
    public ChessBoard(int boardSize, int boardIndex) {
        super(boardSize);
        setPreferredSize(new Dimension(boardSize * ChessSquare.SQUARE_SIZE, boardSize * ChessSquare.SQUARE_SIZE));
        // Set the layout of the panel to a grid with the specified board size
        setLayout(new GridLayout(boardSize, boardSize));
        this.squares = new ChessSquare[boardSize][boardSize];
        this.boardSize = boardSize;
        this.boardIndex = boardIndex;
        initializeBoard();
    }

    /**
     * Initializes the chessboard by placing chess pieces in their starting positions.
     */
    public void initializeBoard() {
        boolean isLightSquare = true;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                squares[row][col] = new ChessSquare(row, col, isLightSquare, null);
                squares[row][col].setBoardIndex(this.boardIndex);
                isLightSquare = !isLightSquare;
                add(squares[row][col]);
            }
            isLightSquare = !isLightSquare;
        }
    }

    /**
     * Add pieces to the board.
     */
    public void initializePieces() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                ChessPiece piece = null;
                // Add black pieces on the first two rows
                if (row == 0) {
                    if (col == 0 || col == 7) {
                        piece = new Rook(Color.BLACK);
                    } else if (col == 1 || col == 6) {
                        piece = new Knight(Color.BLACK);
                    } else if (col == 2 || col == 5) {
                        piece = new Bishop(Color.BLACK);
                    } else if (col == 3) {
                        piece = new Queen(Color.BLACK);
                    } else if (col == 4) {
                        piece = new King(Color.BLACK);
                    }
                } else if (row == 1) {
                    piece = new Pawn(Color.BLACK);
                    // Add white pieces on the last two rows
                } else if (row == 6) {
                    piece = new Pawn(Color.WHITE);
                } else if (row == 7) {
                    if (col == 0 || col == 7) {
                        piece = new Rook(Color.WHITE);
                    } else if (col == 1 || col == 6) {
                        piece = new Knight(Color.WHITE);
                    } else if (col == 2 || col == 5) {
                        piece = new Bishop(Color.WHITE);
                    } else if (col == 3) {
                        piece = new Queen(Color.WHITE);
                    } else if (col == 4) {
                        piece = new King(Color.WHITE);
                    }
                }
                // Set the piece on the ChessSquare
                squares[row][col].setPiece(piece);
                add(squares[row][col]); // Add the ChessSquare to the board
            }
        }
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(boardSize * ChessSquare.SQUARE_SIZE, boardSize * ChessSquare.SQUARE_SIZE);
    }

    public ChessSquare[][] getSquares() {
        return squares;
    }
}
