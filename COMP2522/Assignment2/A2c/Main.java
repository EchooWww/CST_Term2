import javax.swing.*;

/**
 * The Main class represents the entry point of the 3D Chess Game application.
 */
public class Main {
    /**
     * The main method creates and displays the 3D Chess Game window with a centered chessboard.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create the main game window (JFrame)
        JFrame frame = new JFrame("3D Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new 3D chessBoard and add it to the game window
        ChessBoard3D chessBoard3D = new ChessBoard3D(8); // Assuming 8x8 for each 3D board
        frame.add(chessBoard3D);

        // Pack the frame to fit the preferred size of its components
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible to the user
    }
}
