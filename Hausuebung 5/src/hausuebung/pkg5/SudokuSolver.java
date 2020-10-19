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
        boolean erg = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if () {
                    
                }
                
            }
        }
        return erg;
    }

    @Override
    public int[][] solveSudoku(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    @Override
    public int[][] solveSudokuParallel(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    // add helper methods here if necessary
}
