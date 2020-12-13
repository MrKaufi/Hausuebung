/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author flori
 */
public class Streams {

    public double average(int[] numbers) {
        IntStream stream = Arrays.stream(numbers);
        return stream.average().getAsDouble();
    }

    public List<String> upperCase(String[] strings) {
        Stream<String> stream = Arrays.stream(strings);
        return stream.map(name -> name.toUpperCase())
                .collect(Collectors.toCollection(ArrayList::new));
        /*
        List<String> erg = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            erg.add(strings[i].toUpperCase());
        }
        return erg;
         */
    }

    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        if (weapons == null || weapons.isEmpty())
            return null;

        return weapons.stream().min((w1, w2) -> w1.getDamage() - w2.getDamage()).get();

        /*
        Weapon erg = weapons.get(0);
        for (int i = 0; i < weapons.size(); i++) {
            if (erg.getDamage() > weapons.get(i).getDamage()) {
                erg = weapons.get(i);
            }
        }
        return erg;
         */
    }

    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        if (weapons == null || weapons.isEmpty())
            return null;

        return weapons.stream().max((w1, w2) -> w1.getMinStrength() - w2.getMinStrength()).get();
        /*
        Weapon erg = weapons.get(0);
        for (Weapon weapon : weapons) {
            if (weapon.getStrength() > erg.getStrength()) {
                erg = weapon;
            }
        }
        return erg;
         */
    }

    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        return weapons.stream()
                .filter(weapon -> weapon.dT.equals(DamageType.MISSILE))
                .collect(Collectors.toList());
        /*
        List<Weapon> erg = new ArrayList<>();
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getDamageType().toString().equals("MISSILE")) {
                erg.add(weapons.get(i));
            }
        }
        return erg;
         */
    }

    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        if (weapons == null || weapons.isEmpty())
            return null;

        return weapons.stream().max((w1, w2) -> w1.getName().length() - w2.getName().length()).get();
        /*
        Weapon erg = weapons.get(0);
        for (Weapon weapon : weapons) {
            if (weapon.getName().length() > erg.getName().length()) {
                erg = weapon;
            }
        }
        return erg;S
         */
    }

    public List<String> toNameList(List<Weapon> weapons) {
        return weapons.stream()
                .map(Weapon::getName)
                .collect(Collectors.toList());
        /*
        List<String> erg = new ArrayList();
        for (int i = 0; i < weapons.size(); i++) {
            erg.add(weapons.get(i).getName());
        }
        return erg;
         */
    }

    public int[] toSpeedArray(List<Weapon> weapons) {
        return weapons.stream()
                .map(Weapon::getSpeed)
                .mapToInt(x -> x)
                .toArray();
        /*
        int[] erg = new int[weapons.size()];
        for (int i = 0; i < weapons.size(); i++) {
            erg[i] = weapons.get(i).getSpeed();
        }
        return erg;
         */
    }

    public int sumUpValues(List<Weapon> weapons) {
        return weapons.stream()
                .map(Weapon::getValue)
                .mapToInt(x -> x)
                .sum();
        /*
        int erg = 0;
        for (int i = 0; i < weapons.size(); i++) {
            erg += weapons.get(i).getValue();
        }
        return erg;
         */
    }

    public long sumUpHashCodes(List<Weapon> weapons) {
        return weapons.stream()
                .map(Weapon::hashCode)
                .mapToLong(x -> x)
                .sum();
        /*
        long erg = 0;
        for (int i = 0; i < weapons.size(); i++) {
            erg += weapons.get(i).hashCode();
        }
        return erg;
         */
    }

    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        return weapons.stream()
                .distinct()
                .collect(Collectors.toList());
        /*
        Set<Weapon> ergSet = new HashSet<>(weapons);
        List<Weapon> erg = new ArrayList<>();
        erg.addAll(ergSet);
        return erg;
         */
    }

    public void increaseValuesByTenPercent(List<Weapon> weapons) {
        if (weapons == null || weapons.isEmpty()) {
            return;
        }

        weapons.forEach(w -> w.setValue((int) (w.getValue() * 1.1d)));
    }
    /*
        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).value = (weapons.get(i).getValue() / 10) * 11;
        }
     */
}

