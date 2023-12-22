public class Shape {
    int width;
    int height;
    char[][] shape;
    char letter;

    /**
     * The constructor for super class Shape
     * @param width to specify the shape's width
     * @param height to specify the shape's height
     * @param t the string passed in to specify the type of shape
     */
    protected Shape(final int width, final int height, final char t) {
        this.width = width;
        this.height = height;
        this.letter = t;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char getCharAt(int row, int col) {
        return shape[row][col];
    }
}
