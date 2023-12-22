import java.util.Arrays;

/**
 * The subclass Diamond: use `width` for both width and height, `#` as the sign
 */
public class Diamond extends Shape{
    /**
     * The constructor for subclass Diamond
     * Fill out the array of the actual shape of Diamond based on the Shape object created
     */
    public Diamond(int width, int height) throws BadWidthException{
        super(width, width, '#');
        if (width % 2 == 0) throw new BadWidthException();
        char[][] diamond= new char[super.height][super.width];
        for (char[] row: diamond) Arrays.fill(row, ' ');
        for (int i = 0; i < super.height; i++) {
            for (int j = Math.abs(super.width / 2 - i); j <= super.width / 2 + Math.min(i,super.width -i -1); j++) {
                diamond[i][j]= this.letter;
            }
        }
        super.shape = diamond;
    }
}
