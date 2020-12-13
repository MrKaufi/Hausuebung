/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg10;

/**
 *
 * @author Florian
 */
public class Cracker implements Runnable {

    String password;
    int passwordLength;
    char[] part;
    boolean cracked = false;

    public Cracker(String password, int partN, char[] part, int passwordLength) {
        this.password = password;
        this.passwordLength = passwordLength;
        this.part = part;
    }
    
    private static void swap(char[] word, int index1, int index2){
        char temp = word[index1];
        word[index1] = word[index2];
        word[index2] = temp;
    } 
    
    private void permuteAndCheck(char[] word, int start){
        if (start < word.length) {
            for (int i = start; i < word.length; i++) {
                swap(word, 0, i);
                permuteAndCheck(word, start + 1);
                swap(word, start, i);
            }
        }
        
    }
    
    @Override
    public void run() {
        char[] word;
        while (!cracked) {
//            for (int i = 0; i < 4; i++) {
//                erg += part[(int)(Math.random()*part.length)];
//            }
                for (int i = 0; i < part.length; i++) {
                    swap(word, 0, i);
                    permuteAndCheck(word, 1);
                    swap(word, 0, i);
            }
        }
    }
}