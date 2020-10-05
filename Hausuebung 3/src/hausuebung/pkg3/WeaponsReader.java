/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author flori
 */
public class WeaponsReader {

    String[] weaponsString;
    List<Weapon> weapons = new ArrayList<Weapon>();

    public void readCsv() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner("weapons.csv");
        sc.nextLine();
        while (sc.hasNext()) {
            weaponsString = sc.next().split(";");
            weapons.add(new Weapon(weaponsString[0],
                    CombatType.valueOf(weaponsString[1]),
                    DamageType.valueOf(weaponsString[2]),
                    Integer.parseInt(weaponsString[3]),
                    Integer.parseInt(weaponsString[4]),
                    Integer.parseInt(weaponsString[5]),
                    Integer.parseInt(weaponsString[6])));
        }
    }
}
