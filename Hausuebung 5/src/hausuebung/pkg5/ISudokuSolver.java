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
public interface ISudokuSolver {
    int[][] readSudoku(File file);
    
    boolean checkSudoku(int[][] rawSudoku);
    
    int[][] solveSudoku(int[][] rawSudoku);   
    
    int[][] solveSudokuParallel(int[][] rawSudoku);  
}
