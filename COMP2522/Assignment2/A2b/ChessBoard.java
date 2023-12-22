import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ChessBoard class represents a graphical chess board with squares for chess pieces.
 */
public class ChessBoard extends JPanel {
    private ChessSquare[][] squares;
    private ChessSquare selectedSquare;
    private int boardSize;
    private Color currentPlayer;

    /**
     * Creates a new ChessBoard instance with a grid of chess squares.
     *
     * @param boardSize The size of the chessboard (e.g., 8 for an 8x8 board).
     */
    public ChessBoard(int boardSize) {
        setPreferredSize(new Dimension(boardSize * ChessSquare.SQUARE_SIZE, boardSize * ChessSquare.SQUARE_SIZE));
        // Set the layout of the panel to a grid with the specified board size
        setLayout(new GridLayout(boardSize, boardSize));
        this.squares = new ChessSquare[boardSize][boardSize];
        this.boardSize = boardSize;
        initializeBoard();
        addMouseListeners();
        currentPlayer = Color.WHITE;
    }

    /**
     * Initializes the chessboard by placing chess pieces in their starting positions.
     */
    public void initializeBoard() {
        boolean isLightSquare = true;
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
                squares[row][col] = new ChessSquare(row, col, isLightSquare, piece);
                isLightSquare = !isLightSquare;
                add(squares[row][col]);
            }
            isLightSquare = !isLightSquare;
        }
    }

    /**
     * Adds mouse listeners to all chess squares for user interaction.
     */
    public void addMouseListeners() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                squares[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ChessSquare clickedSquare = (ChessSquare) e.getSource();
                        // Check if the square contains a piece of the current player's color
                        if ( clickedSquare.getPiece() != null && clickedSquare.getPiece().getColor() == currentPlayer) {
                            if (selectedSquare != null) {
                                // Deselect the previously selected square
                                selectedSquare.deselect();
                            }
                            // Select the new square
                            selectedSquare = clickedSquare;
                            selectedSquare.select();
                        } else if (selectedSquare != null) {
                            // Move the piece from the selected square to the clicked square
                            if (selectedSquare.getPiece().isValidMove(squares, selectedSquare.getRow(), selectedSquare.getCol(), clickedSquare.getRow(), clickedSquare.getCol()))
                            {
                                moveChessPiece(selectedSquare, clickedSquare);
                                // Deselect the selected square
                                selectedSquare.deselect();
                                selectedSquare = null;
                                // Switch the current player
                                currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
                            } 
                        }
                    }
                });
            }
        }
    }

    /**
     * Moves a chess piece from the source square to the target square.
     *
     * @param sourceSquare The square from which the piece is moved.
     * @param targetSquare The square to which the piece is moved.
     */
    public void moveChessPiece(ChessSquare sourceSquare, ChessSquare targetSquare) {
        // Get the piece from the source square
        ChessPiece pieceToMove = sourceSquare.getPiece();

        // Clear the source square
        sourceSquare.setPiece(null);
        sourceSquare.deselect(); // Deselect the source square

        // Set the piece on the target square
        targetSquare.setPiece(pieceToMove);
        targetSquare.deselect(); // Deselect the target square
        targetSquare.repaint(); // Repaint the target square
    }
}
