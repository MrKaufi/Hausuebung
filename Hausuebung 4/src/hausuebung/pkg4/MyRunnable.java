/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.util.List;

/**
 *
 * @author flori
 */
public class MyRunnable implements Runnable{
    int teiler;
    int part;
    List<String> allNumbers;
    int chunks;
    String[] numbers;

    public MyRunnable(int teiler, List<String> allNumbers, int chunks, int part) {
        this.teiler = teiler;
        this.numbers = new String[allNumbers.size()/chunks];
        this.part = part;
        this.allNumbers = allNumbers;
        this.chunks = chunks;
    }
    
    
    
    
    @Override
    public void run() {
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length*part; j < numbers.length*part+numbers.length; j++) {
                numbers[i] = allNumbers.get(j);
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (Integer.parseInt(numbers[i]) % teiler == 0) {
                System.out.println(numbers[i]);
            }
        }
    }
    
}
