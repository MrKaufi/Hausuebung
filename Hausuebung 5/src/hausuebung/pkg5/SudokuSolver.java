/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author flori
 */
public class SudokuSolver implements ISudokuSolver {

    int[][] sudoku = new int[9][9];

    public SudokuSolver() {
        //initialize if necessary
    }

    @Override
    public final int[][] readSudoku(File file) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            IntStream.range(0, 9)
                    .forEach(r -> IntStream.range(0, 9).
                    forEach(c -> {
                        try {
                            int result = Character.getNumericValue(br.read());
                            br.skip(1);
                            sudoku[r][c] = result;
                        } catch (IOException ex) {
                            Logger.getLogger(SudokuSolver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }));
        } catch (IOException ex) {
            Logger.getLogger(SudokuSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + ";");
            }
            System.out.println("");
        }
        return sudoku;
    }

    @Override
    public boolean checkSudoku(int[][] rawSudoku) {
        boolean r = false;
        boolean c = false;
        boolean b = false;
        for (int i = 0; i < 9; i++) {
            if (!checkRow(sudoku, i)) {
                r = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!checkColumn(sudoku, i)) {
                c = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 0; j++) {
                if (!checkBox(sudoku, i, j)) {
                    b = false;
                }
            }

        }
        if (r && c && b) {
            return true;
        }

        return false;
    }

    @Override
    public int[][] solveSudoku(int[][] rawSudoku) {
        if (checkSudoku(rawSudoku)) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    for (int i = 0; i < 9; i++) {
                        
                    }
                }
            }
            return null;
        } else {
            return rawSudoku;
        }
    }

    @Override
    public int[][] solveSudokuParallel(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    // add helper methods here if necessary
    public boolean checkRow(int[][] board, int row) {
        boolean[] constraint = new boolean[9];
        return IntStream.range(0, 9)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    public boolean checkColumn(int[][] board, int column) {
        boolean[] constraint = new boolean[9];
        return IntStream.range(0, 9)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    public boolean checkBox(int[][] board, int column, int row) {
        boolean[] constraint = new boolean[9];
        int subsectionRowStart = (row / 9) * 9;
        int subsectionRowEnd = subsectionRowStart + 9;

        int subsectionColumnStart = (column / 9) * 9;
        int subsectionColumnEnd = subsectionColumnStart + 9;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != 0) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
