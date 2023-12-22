import java.util.Arrays;

/**
 * The subclass Rectangle: use `width` for both width and  height, `@` as the sign
 */
public class RTriangle extends Shape {
    /**
     * The constructor for subclass RTriangle
     * Fill out the array of the actual shape of RTriangle based on the Shape object created
     */
    public RTriangle(int width, int height) {
        super(width, width, '@');

        char[][] rTriangle = new char[super.height][super.width];
        for (char[] row: rTriangle) Arrays.fill(row, ' ');
        for (int i = 0; i < super.height; i++) {
            for (int j = super.width - 1; j >= super.width - i - 1 ;j--) {
                rTriangle[i][j]= this.letter;
            }
        }
        super.shape = rTriangle;
    }
}
