/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author flori
 */
public class NumberTester {
    BufferedReader reader;
    NumberTest oddTester;
    NumberTest primeTester;
    NumberTest palindromeTester;
    
    public NumberTester(String fileName) {
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NumberTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setOddEvenTester(NumberTest oddTester){
        this.oddTester = oddTester;
    }
    
    public void setPrimeTest(NumberTest primeTester){
        this.primeTester = primeTester;
    }
    
    public void setPalindromeTester(NumberTest palindromeTester){
        this.palindromeTester = palindromeTester;
    }
    
    public void testFile(){
        int left;
        int right;
        try {
            String line = reader.readLine();
            while(reader.ready()){
                line = reader.readLine();
                String[] ar = line.split(" ");
                left = ar[0];
                right = ar[1];
                System.out.println(left + " " + right);
            }
        } catch (IOException ex) {
            Logger.getLogger(NumberTester.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
}
