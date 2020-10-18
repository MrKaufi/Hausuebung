/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author flori
 */
public class Hausuebung3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WeaponsReader wr = new WeaponsReader();

        wr.readCsv();

        List<Weapon> list = wr.getWeapons();
        Stream<Weapon> weaponStream = list.stream();

        final Predicate<Integer> isEven = (t) -> {
            if (t % 2 == 0) {
                return true;
            }
            return false;
        };
        final Predicate<Integer> isOdd = (t) -> {
            if (t % 2 == 0) {
                return false;
            }
            return true;
        };
        final IntPredicate isPositive = (value) -> {
            if (value >= 0) {
                return true;
            }
            return false;
        };
        final Predicate<Integer> isNull = (t) -> {
            if (t == null) {
                return true;
            }
            return false;
        };
        final Predicate<String> isShortWord = (t) -> {
            if (t.length() <= 4) {
                return true;
            }
            return false;
        };

        int[] intArray = new int[10000];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) (Math.random() * 100);
        }
        
        weaponStream.filter(isEven.and(isPositive));
        weaponStream.filter(isPositive.and(isOdd));
                
        String[] stringArray = new String[10];
        for (int i = 0; i < stringArray.length; i++) {
            byte[] array = new byte[10];
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            stringArray[i] = generatedString;
        }
        for (String string : stringArray) {
            System.out.println(string);
        }

        list.sort((Weapon w1, Weapon w2) -> Integer.compare(w1.getDamage(), w2.getDamage()));
        list.sort((Weapon w1, Weapon w2) -> w1.getCombatType().toString().compareTo(w2.getCombatType().toString()));
        list.sort((Weapon w1, Weapon w2) -> w1.getDamageType().toString().compareTo(w2.getDamageType().toString()));
        list.sort((Weapon w1, Weapon w2) -> w1.getName().compareTo(w2.getName()));
        
        weaponStream.forEach(w -> System.out.println(w.toString()));

        /*list.sort((Weapon w1, Weapon w2) -> {
            int offset = 0;
            for (int i = 0; i < list.size(); i++) {
                offset = Integer.compare(w1.getdT().toString().hashCode(), w2.getcT().toString().hashCode());
                if (offset < 0) {
                    
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Integer.compare(w1.getdT().toString().hashCode(), w2.getdT().toString().hashCode());
            }
            for (int i = 0; i < list.size(); i++) {
                Integer.compare(w1.getName().toString().hashCode(), w2.getName().toString().hashCode());
            }
            
            return null;
        });*/
 /*
        Printable printConsole = (weapons) -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(weapons.get(i).getName() + ";");
                System.out.print(weapons.get(i).getCombatType()+ ";");
                System.out.print(weapons.get(i).getCombatType() + ";");
                System.out.print(weapons.get(i).getDamage() + ";");
                System.out.print(weapons.get(i).getSpeed() + ";");
                System.out.print(weapons.get(i).getStrength() + ";");
                System.out.println(weapons.get(i).getValue());
            }
        };
        
        Printable makeCsv = (weapons) -> {
            try {
                FileWriter fileWriter = new FileWriter("newWeapons.csv");

                for (int i = 0; i < list.size(); i++) {
                    fileWriter.append(weapons.get(i).getName());
                    fileWriter.append(weapons.get(i).getCombatType().toString());
                    fileWriter.append(weapons.get(i).getCombatType().toString());
                    fileWriter.append(String.valueOf(weapons.get(i).getDamage()));
                    fileWriter.append(String.valueOf(weapons.get(i).getSpeed()));
                    fileWriter.append(String.valueOf(weapons.get(i).getStrength()));
                    fileWriter.append(String.valueOf(weapons.get(i).getValue()));
                }
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException ex) {
                Logger.getLogger(Hausuebung3.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        printConsole.print(list);
        makeCsv.print(list);
         */
    }

}
