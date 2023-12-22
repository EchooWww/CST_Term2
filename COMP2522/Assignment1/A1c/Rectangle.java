import java.util.Arrays;

/**
 * The subclass rectangle: fill out a rectangle
 */
public class Rectangle extends Shape{
    /**
     * The constructor for subclass Rectangle
     * Fill out the array of actual shape of Rectangle based on the Shape object created
     */
    public Rectangle(int width, int height) {
        super(width, height, "r");
        char[][] rectangle = new char[super.height][super.width];
        for (char[] row: rectangle) Arrays.fill(row, '*');
        super.shape = rectangle;
    }
}
