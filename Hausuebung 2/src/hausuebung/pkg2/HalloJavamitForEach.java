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
        NumberTester nt = new NumberTester("Hausuebung2 zahlen.txt");
        List<String> list = new ArrayList<String>();
        
        list.add("Hello");
        list.add("There");
        list.add("OooOOooOOoo");
        list.add("OooOOoOOoo");
        list.add("OooOOooOOooooo");
        list.forEach((String s) -> System.out.println(s));
        
        nt.testFile();
    }   
}
