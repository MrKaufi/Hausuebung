/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flori
 */
public class Streams {
    public double average(int[] numbers) {
        double erg = 0;
        for (int i = 0; i < numbers.length; i++) {
            erg =+ numbers[i];
        }
        return erg/numbers.length;
    }
    
    public List<String> upperCase(String[] strings) {
        List<String> erg = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            erg.add(strings[i].toUpperCase());
        }
        return erg;
    }
    
    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        Weapon erg = weapons.get(0);
        for (int i = 0; i < weapons.size(); i++) {
            if (erg.getDamage() > weapons.get(i).getDamage()) {
                erg = weapons.get(i);
            }
        }
        return erg;
    }
    
    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        Weapon erg = weapons.get(0);
        for (int i = 0; i < weapons.size(); i++) {
            if (erg.getStrength() > weapons.get(i).getStrength()) {
                erg = weapons.get(i);
            }
        }
        return erg;
    }
    
    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        List<Weapon> erg = new ArrayList<>();
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getdT().toString().equals("MISSILE")) {
                erg.add(weapons.get(i));
            }
        }
        return erg;
    }
    
    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        Weapon erg = weapons.get(0);
        for (int i = 0; i < weapons.size(); i++) {
            if (erg.getName().length() < weapons.get(i).getName().length()) {
                erg = weapons.get(i);
            }
        }
        return erg;
    }
    
    public List<String> toNameList(List<Weapon> weapons) {
        List<String> erg = new ArrayList();
        for (int i = 0; i < weapons.size(); i++) {
            erg.add(weapons.get(i).getName());
        }
        return erg;
    }
    
    public int[] toSpeedArray(List<Weapon> weapons) {
        List<String> erg = new ArrayList();
        for (int i = 0; i < weapons.size(); i++) {
            erg.add(weapons.get(i).getName());
        }
        return erg;
    }
    
    public int sumUpValues(List<Weapon> weapons) {
        //implement this
    }
    
    public long sumUpHashCodes(List<Weapon> weapons) {
        //implement this
    }
    
    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        //implement this
    }
    
    public void increaseValuesByTenPercent(List<Weapon> weapons) {
       //implement this
    }
}
