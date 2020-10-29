/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        int c = 5;//can be changed later
        int teiler = 924;//can be changed later

        String[][] chunks = new String[c][numbers.size() / c];
        String[] chunk = new String[chunks.length];
        
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(c);
        //Beispiel 1:
//        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i));
//        }
//        for (int i = 0; i < c; i++) {
//            for (int j = 0; j < numbers.size() / c; j++) {
//                chunks[i][j] = numbers.get(j);
//            }
//        }
//
//
//        for (int i = 0; i < c; i++) {
//
//            chunk = chunks[i];
//
//            MyRunnableDivider myRunable = new MyRunnableDivider(chunk, i, teiler);
//            System.out.println("Task " + i + ": Created");
//            executor.execute(myRunable);
//
//        }
//        executor.shutdown();

        //Beispiel 2:
        Set<Future<Integer>> set = new HashSet<>();
        
        int gauss = 210;//can be changed later
        int[] ergs = new int[c];
        int erg = 0;
        int[] g = new int[gauss + 1];

        for (int i = 1; i < g.length; i++) {
            g[i] = i;
        }
        for (int i = 0; i < g.length; i++) {
            System.out.println(g[i]);
        }

        for (int i = 0; i < c; i++) {
            int[] p = new int[g.length / c];
            for (int j = 0; j < p.length; j++) {
                p[j] = g[j + (p.length * i)];
            }
            Callable<Integer> gaussCallable = new GaussCallable(p, i);
            System.out.println("Task " + i + ": Created");
            Future<Integer> future = executor.submit(gaussCallable);
            set.add(future);
        }
        
        for (Future<Integer> future : set) {
            try {
                erg += future.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(Hausuebung4.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Hausuebung4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Gauss von" + gauss + ": " + erg);
        
        executor.shutdown();
    }
}
