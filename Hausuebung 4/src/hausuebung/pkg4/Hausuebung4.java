/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        int c = 2;//can be changed
        int teiler = 924;//can be changed
        String[][] chunks = new String[c][numbers.size() / c];

        String[] chunk = new String[chunks.length];

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < numbers.size() / c; j++) {
                chunks[i][j] = numbers.get(j);
            }
        }

//        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i));
//        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(c);

        for (int i = 0; i < c; i++) {

            chunk = chunks[i];

            MyRunnable myRunable = new MyRunnable(chunk, i, teiler);
            System.out.println("Task " + i + ": Created");
            executor.execute(myRunable);

        }
        executor.shutdown();

//        Thread[] threads = new Thread[c];
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(new MyRunnable(teiler, numbers, c, i));
//        }
    }

}
