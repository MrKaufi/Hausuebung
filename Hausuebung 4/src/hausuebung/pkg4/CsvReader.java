/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author flori
 */
public class CsvReader {

    File f = new File("numbers.csv");
    List<String> numbers = new ArrayList<String>();
    String s = "";

    public List<String> readCsv() throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            s = sc.nextLine();
            String[] strings = s.split(":");
            for (int i = 0; i < strings.length; i++) {
                if (!notNumber(strings[i])) {
                    numbers.add(strings[i]);
                }
            }
        }
        return numbers;

    }

    public boolean notNumber(String wert) {

        try {
            int i = Integer.parseInt(wert);
            return false;
        } catch (NumberFormatException ex){
            return true;
        }

    }
}
