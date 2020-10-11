/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.util.List;

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
        Printable print = (weapons) -> {
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
        print.print(list);
    }

}
