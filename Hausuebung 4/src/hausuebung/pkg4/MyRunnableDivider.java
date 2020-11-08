/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;


/**
 *
 * @author flori
 */
public class MyRunnableDivider implements Runnable {

    int teiler;
    int part;
    String[] chunk;

    public MyRunnableDivider(String[] chunk, int part, int teiler) {
        this.teiler = teiler;
        this.part = part;
        this.chunk = chunk;
    }

    @Override
    public void run() {
        for (int i = 0; i < chunk.length; i++) {
            if (Integer.parseInt(chunk[i]) % teiler == 0) {
                System.out.println(chunk[i]);
            }
        }
//        
//        
//        for (int i = 0; i < chunks.length; i++) {
//            int start = i * chunks.length;
//            int length = Math.min(i, i)
//        }
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = numbers.length * part; j < numbers.length * part + numbers.length; j++) {
//                numbers[i] = allNumbers.get(j);
//            }
//            System.out.println(numbers[i]);
//        }
//        for (int i = 0; i < numbers.length; i++) {
//            if (Integer.parseInt(numbers[i]) % teiler == 0) {
//                System.out.println(numbers[i]);
//            }
//        }
    }

}
