/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Streams streams = new Streams();
        wr.readCsv();
        
        List<Weapon> list = wr.getWeapons();
        
        
        list.sort((Weapon w1, Weapon w2) -> Integer.compare(w1.getDamage(), w2.getDamage()));

        list.sort((Weapon w1, Weapon w2) -> w1.getcT().toString().compareTo(w2.getcT().toString()));
        //list.sort((Weapon w1, Weapon w2) -> w1.getdT().toString().compareTo(w2.getdT().toString()));
        //list.sort((Weapon w1, Weapon w2) -> w1.getName().toString().compareTo(w2.getName().toString()));

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
        });
         */
        Printable printConsole = (weapons) -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(weapons.get(i).getName() + ";");
                System.out.print(weapons.get(i).getcT() + ";");
                System.out.print(weapons.get(i).getdT() + ";");
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
                    fileWriter.append(weapons.get(i).getcT().toString());
                    fileWriter.append(weapons.get(i).getdT().toString());
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
    }

}
