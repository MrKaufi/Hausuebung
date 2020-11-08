/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.util.concurrent.Callable;

/**
 *
 * @author Florian
 */
public class GaussCallable implements Callable {

    int[] numbers;
    int part;

    public GaussCallable(int[] numbers, int part) {
        this.numbers = numbers;
        this.part = part;
    }

    @Override
    public Object call() throws Exception {
        int erg = 0;

        for (int i = 0; i < numbers.length; i++) {
            erg += numbers[i];
        }
        return erg;
    }

}
