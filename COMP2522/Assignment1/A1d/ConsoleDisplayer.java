public class ConsoleDisplayer implements Displayer{
    /**
     * Class ConsoleDisplayer: display the shapes in the console
     */
    @Override
    /*
      Provide an implementation of displayTable: with console print
     */
    public void displayTable(Shape shape) {
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                System.out.printf("%c", shape.getCharAt(i, j));
            }
            System.out.println();
        }
    }
}
