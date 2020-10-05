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

    public void setOddEvenTester(NumberTest oddTester) {
        this.oddTester = oddTester;
    }

    public void setPrimeTest(NumberTest primeTester) {
        this.primeTester = primeTester;
    }

    public void setPalindromeTester(NumberTest palindromeTester) {

        this.palindromeTester = palindromeTester;
    }

    public void testFile() {
        NumberTest isOdd = (n) -> {
            if ((n % 2) == 0) {
                return true;
            } else {
                return false;
            }

        };
        NumberTest isPrime = (n) -> {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };
        NumberTest isPalindrome = (n) -> {
            String sN = Integer.toString(n);
            StringBuilder sb = new StringBuilder(sN);

            if (sN.equals(sb.reverse().toString())) {
                return true;
            } else {
                return false;
            }
        };
        int left;
        int right;
        try {
            String line = reader.readLine();
            while (reader.ready()) {
                line = reader.readLine();
                String[] ar = line.split(" ");
                left = Integer.parseInt(ar[0]);
                right = Integer.parseInt(ar[1]);
                switch (left) {

                    case 1:
                        setOddEvenTester(isOdd);
                        if (oddTester.testNumber(right)) {
                            System.out.println("ODD");
                        } else {
                            System.out.println("EVEN");
                        }
                        break;

                    case 2:
                        setPrimeTest(isPrime);
                        if (primeTester.testNumber(right)) {
                            System.out.println("PRIME");
                        } else {
                            System.out.println("NO PRIME");
                        }
                        break;

                    case 3:
                        setPalindromeTester(isPalindrome);
                        if (palindromeTester.testNumber(right)) {
                            System.out.println("PALINDROME");
                        } else {
                            System.out.println("NO PALINDROME");
                        }
                        break;

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(NumberTester.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
