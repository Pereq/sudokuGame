package pl.lodz.p.sudoku;

import java.sql.SQLException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final DataBase dataBase;

    public JdbcSudokuBoardDao() {
        this.dataBase = new DataBase();
    }

    @Override
    public SudokuBoard read(String name) throws IndexOutOfRangeException {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard result = new SudokuBoard(solver);
        try {
            int boardId = dataBase.selectBoardId(name);
            for (int i = 0;i < 9;i++) {
                for (int j = 0;j < 9;j++) {
                    int value = dataBase.selectValueOfField(boardId,i,j);
                    result.setValue(i,j,value);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void write(SudokuBoard obj, String name) throws MyException {

        try {
            if (dataBase.isBoardOfNameInBase(name)) {
                removeBoard(name);
            }
            dataBase.insertNewBoardToDatabase(name);
            int boardId = dataBase.selectBoardId(name);

            for (int i = 0;i < 9;i++) {
                for (int j = 0;j < 9;j++) {
                    dataBase.insertNewFieldToDatabase(boardId,i,j,obj.getValue(i,j));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBoard(String name) throws SQLException {
        int id = dataBase.selectBoardId(name);
        dataBase.insertToDatabase("DELETE FROM field WHERE boardid='" + id + "';");
        dataBase.insertToDatabase("DELETE FROM board WHERE name='" + name + "';");
    }

    @Override
    public void close() throws SQLException {
        this.dataBase.getConnection().close();
    }
}
