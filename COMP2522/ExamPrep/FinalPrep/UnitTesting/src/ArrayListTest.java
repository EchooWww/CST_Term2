import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArrayListTest {

    @Test
    public void constructorTest() {
        // negative size
        Assert.assertThrows(NegativeArraySizeException.class, () -> {
            ArrayList list = new ArrayList(-1);
        });
    }

    @Test
    public void sizeTest() {
        // default size is correct
        ArrayList list = new ArrayList();
        Assert.assertEquals(10, list.size());
        // after add size check
        list.add(5);
        Assert.assertEquals(10, list.size());
        // after adding more than current size check
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5); // 11th element
        Assert.assertEquals(20, list.size());
    }

    @Test
    public void addTest() {
        // adding a single object
        ArrayList list = new ArrayList();
        list.add(0);
        Assert.assertEquals(0, list.getAt(0));
        // adding more than current size
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10); // 11th element
        Assert.assertEquals(10, list.getAt(10));
    }

    @Test
    public void getAtTest() {
        // -ve location
        ArrayList list = new ArrayList();
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.getAt(-1);
        });
        // beyond size location
        Assert.assertNull(list.getAt(11));
        // valid location
        list.add(0);
        Assert.assertEquals(0, list.getAt(0));
    }

    @Test
    public void deleteAtTest() {
        // -ve location
        ArrayList list = new ArrayList();
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.deleteAt(-1);
        });
        // beyond size location
        Assert.assertNull(list.deleteAt(11));
        // valid location
        list.add(0);
        Assert.assertEquals(0, list.deleteAt(0));
    }

    @Test
    public void printTest() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        // empty list
        ArrayList list = new ArrayList();
        list.print();
        // full list
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9); // 10th element
        list.print();
        Assert.assertEquals("0 1 2 3 4 5 6 7 8 9 ", outputStreamCaptor.toString());
    }
}
