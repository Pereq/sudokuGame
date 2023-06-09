package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest {

    @Test
    void fileWriteTest() throws Exception {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao file = (FileSudokuBoardDao) factory.createFileSudokuBoardDao("plik");
        file.write(board,"name");
        assertTrue(board.equals(file.read("name")));
        file.close();
    }


    @Test
    void dataBaseWriteTest() throws MyException, SQLException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        JdbcSudokuBoardDao dataBase = (JdbcSudokuBoardDao) factory.createJdbcSudokuBoardDao();
        dataBase.write(board,"name");
        assertFalse(board == dataBase.read("name"));
        assertTrue(board.equals(dataBase.read("name")));
        dataBase.close();
        assertThrows(RuntimeException.class, () -> dataBase.read("name"));
    }
}
