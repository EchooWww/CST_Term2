import java.util.Arrays;

/**
 * The subclass Triangle: height calculated by the `width` passed in constructor.
 */
public class Triangle extends Shape {
    /**
     * The constructor for subclass Triangle
     * Fill out the array of the actual shape of Triangle based on the Shape object created
     */
    public Triangle(int width, int height) throws BadWidthException {
        super(width, width/2 + 1, '@');
        if (width % 2 == 0) throw new BadWidthException();
        char[][] triangle = new char[super.height][super.width];
        for (char[] row: triangle) Arrays.fill(row, ' ');
        for (int i = 0; i < super.height; i++) {
            for (int j = super.width/2 -i; j <= super.width/2 + i; j++) {
                triangle[i][j]= this.letter;
            }
        }
        super.shape = triangle;
    }
}
