/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flori
 */
public class WeaponsReader {
    List<Weapon> weapons = new ArrayList<Weapon>();
    
    public void readCsv(){
    try (BufferedReader br = new BufferedReader(new FileReader(new File("weapons.csv")))) {

            br.readLine();
            String zeile = br.readLine();
            String[] tmp;
            while (null != zeile) {
                tmp = zeile.split(";");
                zeile = br.readLine();
                Weapon w = new Weapon(tmp[0], CombatType.valueOf(tmp[1]), DamageType.valueOf(tmp[2]),Integer.valueOf(tmp[3]),Integer.valueOf(tmp[4]), Integer.valueOf(tmp[5]), Integer.valueOf(tmp[6]));
                weapons.add(w);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }
}
