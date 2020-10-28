/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

/**
 *
 * @author Florian
 */
public class MyRunnableGauss implements Runnable {

    int[] numbers;
    int part;

    public MyRunnableGauss(int[] numbers, int part) {
        this.numbers = numbers;
        this.part = part;
    }

    @Override
    public void run() {
        int erg = 0;
        
        
        for (int i = 0; i < numbers.length; i++) {
            erg += numbers[i];
        }
        System.out.println("Executing Task: " + part + ": erg: " + erg);
    }

}
