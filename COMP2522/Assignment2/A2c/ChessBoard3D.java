import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoard3D extends Board {
    private final ChessBoard[] boards = new ChessBoard[3];
    private ChessSquare selectedSquare;
    private Color currentPlayer;

    public ChessBoard3D(int boardSize) {
        super(boardSize);
        initialize3DBoard(boardSize);
        setPreferredSize(new Dimension(3 * boardSize * ChessSquare.SQUARE_SIZE, boardSize * ChessSquare.SQUARE_SIZE));
        setLayout(new GridLayout(1, 3));
        addMouseListeners();
        currentPlayer = Color.WHITE;
    }

    private void initialize3DBoard(int boardSize) {
        for (int i = 0; i < 3; i++) {
            boards[i] = new ChessBoard(boardSize, i);
            if (i == 0) boards[i].initializePieces();
            boards[i].setLocation(i * boardSize * ChessSquare.SQUARE_SIZE, 0);
            boards[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Set border for each board
            add(boards[i]);
        }
    }

    /**
     * Adds mouse listeners to all chess squares for user interaction.
     */
    public void addMouseListeners() {
        for (int i = 0; i < 3; i++) {
            ChessSquare[][] squares = boards[i].getSquares();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    squares[row][col].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            ChessSquare clickedSquare = (ChessSquare) e.getSource();
                            // Check if the square contains a piece of the current player's color
                            if (clickedSquare.getPiece() != null && clickedSquare.getPiece().getColor() == currentPlayer) {
                                if (selectedSquare != null) {
                                    // Deselect the previously selected square
                                    selectedSquare.deselect();
                                }
                                // Select the new square
                                selectedSquare = clickedSquare;
                                selectedSquare.select();
                            } else if (selectedSquare != null) {
                                // Move the piece from the selected square to the clicked square
                                if (selectedSquare.getPiece().isValidMove(boards, selectedSquare.getRow(), selectedSquare.getCol(), selectedSquare.getBoardIndex(), clickedSquare.getRow(), clickedSquare.getCol(), clickedSquare.getBoardIndex()))
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
        sourceSquare.repaint(); // Repaint the source square

        // Set the piece on the target square
        targetSquare.setPiece(pieceToMove);
        targetSquare.deselect(); // Deselect the target square
        targetSquare.repaint(); // Repaint the target square
    }
}
