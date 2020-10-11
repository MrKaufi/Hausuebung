/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flori
 */
public class HalloJavamitForEach {
    
    public static void main(String[] args) {
        CalculationOperation rCadd = (y,x) -> (y+x);
        RationalCalculator rC = new RationalCalculator(add, subtract, multiply, divide);
        VectorCalculator vC = new VectorCalculator(add, subtract, multiply, divide);
        ComplexCalculator cC = new ComplexCalculator(add, subtract, multiply, divide);
        NumberTester nt = new NumberTester("Hausuebung2 zahlen.txt");
        
        nt.testFile();
    }   
}
