/**
 * The super class Shape: defines common members of all different subclasses of shapes
 */
public class Shape {
    int width;
    int height;
    char[][] shape;

    /**
     * The constructor for super class Shape
     * @param width to specify the shape's width
     * @param height to specify the shape's height
     * @param t the string passed in to specify the type of shape
     */
    protected Shape(final int width, final int height, final String t) {
        this.width = width;
        this.height = height;
    }

    /**
     * To display (print) the Shape
     */
    void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%c", shape[i][j]);
            }
            System.out.println();
        }
    }
}
