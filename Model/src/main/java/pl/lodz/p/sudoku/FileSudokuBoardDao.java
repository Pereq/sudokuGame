package pl.lodz.p.sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws FileNotFoundException, MyException {
        SudokuBoard result = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            result = (SudokuBoard) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new MyException(e.getMessage());
        }

        return result;
    }

    @Override
    public void write(SudokuBoard obj) throws MyException {
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void close() throws MyException {
        try {
            fis.close();
            fos.close();
            oos.close();
            ois.close();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
