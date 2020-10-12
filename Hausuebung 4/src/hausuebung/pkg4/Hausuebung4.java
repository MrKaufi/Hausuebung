/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.io.FileNotFoundException;
import java.util.List;

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

        for (int i = 0; i < numbers.size(); i++) {
            if (!reader.notNumber(numbers.get(i))) {
                System.out.println(numbers.get(i));
            }
        }
    }

}
