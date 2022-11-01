package pl.lodz.p.sudoku;

public class SudokuColumn {

    private SudokuField[] column = new SudokuField[9];

    public SudokuField[] getColumn() {
        return column;
    }

    public void setColumn(SudokuField column,int fieldInColumn) {
        this.column[fieldInColumn] = column;
    }
}
