/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg5;

import java.io.File;

/**
 *
 * @author flori
 */
public class Hausuebung5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        int[][] sudoku = ss.readSudoku(new File("1_sudoku_level1.csv"));
        
        if (ss.checkSudoku(sudoku)) {
            System.out.println("True");
        }
        else System.out.println("False");
        
    }
    
}
