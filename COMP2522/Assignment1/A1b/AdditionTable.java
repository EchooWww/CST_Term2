class AdditionTable extends Table{
    AdditionTable(int start, int stop) {
        super(start, stop);
        super.tableType = TableType.ADD;
        for (int i = 0; i <= stop - start; i++) {
            for (int j = 0; j <= stop - start; j++) {
                super.table[i][j] = (i + start) + (j + start);
            }
        }
    }
}
