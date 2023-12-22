
/**
 * The subclass Square: Special Rectangle which uses `width` for both width and height
 */
public class Square extends Rectangle {

    /**
     * The constructor for subclass Square: special rectangle
     * Call the Rectangle constructor with `width` as both width and height
     * Fill out the array of actual shape of Square
     */
    public Square(int width, int height) {
        super(width, width);
    }
}
