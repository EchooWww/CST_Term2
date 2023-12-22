import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class DisplayerFrame
    extends JFrame
{
    public void init(final Shape shape)
    {
		
       setLayout(new GridBagLayout());
	   GridBagConstraints c = new GridBagConstraints();
	   c.fill = GridBagConstraints.BOTH;
        for(int row = 0; row < shape.getHeight(); row++)
        {

            for(int col = 0; col < shape.getWidth(); col++)
            {
				c.gridx = col;
				c.gridy = row;
                add(new JButton(String.valueOf(shape.getCharAt(row, col))), c);
            }
        }
    }
}
