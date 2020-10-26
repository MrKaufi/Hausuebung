/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author flori
 */
public class Hausuebung4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        CsvReader reader = new CsvReader();
        List<String> numbers = reader.readCsv();
        int chunks = 100;//can be changed
        int teiler = 5;//can be changed
        
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
        
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyRunnable(teiler,numbers, chunks, i));
       }
        
    }

}
