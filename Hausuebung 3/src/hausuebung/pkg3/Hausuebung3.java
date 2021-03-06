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
import java.util.stream.IntStream;
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

        int[] intArray = new int[10000];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) (Math.random() * 100);
        }

        //Sort list and print
        list.sort((Weapon w1, Weapon w2) -> {
            int x = w1.getCombatType().toString().compareTo(w2.getCombatType().toString());
            if (x == 0) {
                int y = w1.getDamageType().toString().compareTo(w2.getDamageType().toString());
                if (y == 0) {
                    int z = w1.getName().compareTo(w2.getName());
                    return z;
                } else {
                    return y;
                }
            } else {
                return x;
            }
        });
        System.out.format("%-20s%-15s%-15s%-10s%-10s%-10s%-10s\n", new String[] {"name", "combatType", "damageType", "damage", "speed", "strength", "value"});
        Printable printConsole = (weapons) -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.format("%-20s%-15s%-15s%-10s%-10s%-10s%-10s\n", weapons.get(i).toString().split(";"));
//                System.out.print(weapons.get(i).getCombatType() + ";");
//                System.out.print(weapons.get(i).getDamage() + ";");
//                System.out.print(weapons.get(i).getSpeed() + ";");
//                System.out.print(weapons.get(i).getStrength() + ";");
//                System.out.println(weapons.get(i).getValue());
            }
        };

        printConsole.print(list);
        System.out.println("");
//        weaponStream.forEach(w -> System.out.println(w.toString()));
//        System.out.println("");

        //Predicates
        final Predicate<Integer> isEven = (t) -> t % 2 == 0;

        final Predicate<Integer> isOdd = (t) -> t % 2 != 0;

        final Predicate<Integer> isPositive = (t) -> t >= 0;

        final Predicate<Integer> isNull = (t) -> t == null;

        final Predicate<String> isShortWord = (t) -> t.length() < 5;

        System.out.println(isEven.test(4));
        System.out.println(isOdd.test(3));
        System.out.println(isPositive.test(1));
        System.out.println(isNull.test(null));
        System.out.println(isShortWord.test("abc"));

        System.out.println(isEven.and(isPositive).test(2));
        System.out.println(isPositive.or(isOdd).test(3));
        
        System.out.println();
        //Beispiel 4
        final int result = IntStream.of(1,2,3,4,5,6,7,8,9,10).filter(x -> x % 2 == 1)
                .map(x -> x * x)
                .reduce(0, Integer::sum);
        
        System.out.println("Beispiel 4: ");
        System.out.println(result);
        //Random Strings
//        String[] stringArray = new String[10];
//        for (int i = 0; i < stringArray.length; i++) {
//            byte[] array = new byte[10];
//            new Random().nextBytes(array);
//            String generatedString = new String(array, Charset.forName("UTF-8"));
//            stringArray[i] = generatedString;
//        }
//        for (String string : stringArray) {
//            System.out.println(string);
//        }
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
