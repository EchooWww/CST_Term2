/**
 * To create an ArithmeticTable object and print a table based on the arguments
 *
 * @author Wang Echo, set 2C
 */
public class ArithmeticTable {
    // Instance variables of ArithmeticTable object
    private int start;
    private int end;
    private float[][] table;
    private TableType tableType;

    // Enum type for table type: ADD for addition, MULT for multiplication
    private enum TableType {
        ADD,
        MULT
    }

    /**
     * argumentCheck(): to check if the arguments are valid before creating tables. Will return false if invalid arguments(s) is passed in.
     *
     * @param args: arguments from the terminal when running the file
     * @return true: means the arguments passed the check, valid
     * false: means the arguments failed to pass the check, invalid
     */
    public boolean argumentCheck(String[] args) {
        // Check if argument number matches our requirement
        if (args.length != 3) {
            return handleError();
        }

        // Check the arithmetic type
        if (args[0].charAt(0) == '+')
            tableType = TableType.ADD;
        else if (args[0].charAt(0) == '*')
            tableType = TableType.MULT;
        else {
            return handleError();
        }
        int start;
        int end;

        // Check if the start and end value are valid integer
        try {
            start = Integer.parseInt(args[1]);
            end = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            return handleError();
        }

        // Check if the start and end value exceed the limit
        if ((start < 1 || start > 100) || ((end < 1 || end > 100))) {
            return handleError();
        }

        // Check if start value is less than end value
        if (start >= end) {
            return handleError();
        }

        // If passed all checks, assign the arguments to instance variables and return true
        this.start = start;
        this.end = end;
        return true;
    }

    /**
     * handleError(): extracted method to handle errors whenever argumentCheck() returns false
     *
     * @return false because we want to return false when we call it
     */
    private boolean handleError() {
        System.err.println("Usage: Main <type> <start> <stop>");
        System.err.println("\tWhere <type> is one of +,\"*\"");
        System.err.println("\tand <start> is between 1 and 100");
        System.err.println("\tand <stop> is between 1 and 100");
        System.err.println("\tand start < stop");
        return false;
    }

    /**
     * createTable(): to create a 2D-array based on the arguments, and assign it to our instance variable table
     *
     * @param begin:     the starting number of the table, inclusive
     * @param finish:    the ending number of the table, inclusive
     * @param tableType: the type of arithmetic the table does, can be ADD or MULT
     */
    public void createTable(int begin, int finish, TableType tableType) {
        // Create a matrix based on begin and finish value
        this.table = new float[finish - begin + 1][finish - begin + 1];
        // A nested loop to
        for (int i = 0; i <= finish - begin; i++) {
            for (int j = 0; j <= finish - begin; j++) {
                // Assign value for each element based on the arguments
                if (tableType.equals(TableType.ADD)) {
                    table[i][j] = i + j + begin * 2;
                } else {
                    table[i][j] = (i + begin) * (j + begin);
                }
            }
        }
    }

    /*
    printTable(): to print or matrix with the specified format
     */
    public void printTable() {
        // print the arithmetic sign
        System.out.printf("%7s", tableType == TableType.MULT ? "*" : "+");
        // print the numbers in horizontal axis
        for (int i = start; i <= end; i++) {
            System.out.printf("%5d", i);
        }
        // print the horizontal line
        int tableWidth = (end - start + 1) * 5;
        System.out.println("\n" + " ".repeat(7) + "-".repeat(tableWidth));

        // print the table
        for (float[] row : table) {
            // print the vertical line and numbers in vertical axis
            System.out.printf("%5d |", start++);
            // print the numbers in table
            for (float num : row) {
                System.out.printf("%5d", (int) num);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();
        }
    }
}