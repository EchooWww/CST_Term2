import javax.swing.JFrame;

/**
 * Class SwingDisplayer: display the shapes in a java Swing window
 */
public class SwingDisplayer implements Displayer {
    @Override
    /*
      Provide one implementation of displayTable: with JFrame and JButton
     */
    public void displayTable(final Shape shape) {
        final DisplayerFrame frame;

        frame = new DisplayerFrame();
        frame.init(shape);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}