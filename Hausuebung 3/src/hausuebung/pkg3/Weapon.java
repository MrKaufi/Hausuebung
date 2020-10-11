/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.util.Objects;

/**
 *
 * @author flori
 */
public class Weapon {

    String name;
    CombatType cT;
    DamageType dT;
    int damage;
    int speed;
    int strength;
    int value;

    public Weapon(String name, CombatType cT, DamageType dT, int damage, int speed, int strength, int value) {
        this.name = name;
        this.cT = cT;
        this.dT = dT;
        this.damage = damage;
        this.speed = speed;
        this.strength = strength;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.cT);
        hash = 11 * hash + Objects.hashCode(this.dT);
        hash = 11 * hash + this.damage;
        hash = 11 * hash + this.speed;
        hash = 11 * hash + this.strength;
        hash = 11 * hash + this.value;
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CombatType getcT() {
        return cT;
    }

    public void setcT(CombatType cT) {
        this.cT = cT;
    }

    public DamageType getdT() {
        return dT;
    }

    public void setdT(DamageType dT) {
        this.dT = dT;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
