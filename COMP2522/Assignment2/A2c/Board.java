import javax.swing.*;
import java.awt.Color;

public abstract class Board extends JPanel {
    protected int boardSize;
    protected ChessSquare[][] squares;

    public Board(int boardSize) {};

    // Common methods that can be shared among subclasses
    public ChessSquare getSquare(int row, int col) {
        return squares[row][col];
    }

    public int getBoardSize() {
        return boardSize;
    }
}
