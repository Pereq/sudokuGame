package pl.lodz.p.sudoku;

import java.util.Random;

public class SudokuBoard {

    private final int[][] board;

    private int[][] initBoard() {
        int[][] result = new int[9][9];
        Random rand = new Random();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (i == 0) {
                    int random = 0;
                    do {
                        random = rand.nextInt(9) + 1;
                    } while (!isRowCorrect(result, i, j, random));
                    result[i][j] = random;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    public int[][] getBoard() {
        return board.clone();
    }

    public int getValue(int x, int y) {
        return board[x][y];
    }

    public void setValue(int x, int y, int value) {
        board[x][y] = value;
    }

    public SudokuBoard() {
        board = initBoard();
    }

    private boolean isRowCorrect(int[][] board, int x, int y, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == number) {
                return false;
            }
        }
        return true;
    }

}
