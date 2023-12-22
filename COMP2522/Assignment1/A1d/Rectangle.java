import java.util.Arrays;

/**
 * The subclass Rectangle: use `width` for  width, `height` for height, `*` as the sign
 */
public class Rectangle extends Shape{
    /**
     * The constructor for subclass Rectangle
     * Fill out the array of actual shape of Rectangle based on the Shape object created
     */
    public Rectangle(int width, int height) {
        super(width, height, '*');
        char[][] rectangle = new char[super.height][super.width];
        for (char[] row: rectangle) Arrays.fill(row, super.letter);
        super.shape = rectangle;
    }
}
