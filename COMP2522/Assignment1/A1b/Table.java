class Table {
    float[][] table;
    TableType tableType;
    private int start;
    private int stop;

    protected Table(int start, int stop) {
        this.start = start;
        this.stop = stop;
        this.table = new float[stop - start + 1][stop - start + 1];
    }

    public void display() {
        // print the arithmetic sign
        System.out.printf("%7s", tableType == TableType.MULT ? "*" : "+");
        // print the numbers in horizontal axis
        for (int i = start; i <= stop; i++) {
            System.out.printf("%5d", i);
        }
        // print the horizontal line
        int tableWidth = (stop - start + 1) * 5;
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
}
