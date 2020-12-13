/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

/**
 *
 * @author Florian
 */
public class Zusatzaufgabe {

    public static void main(String[] args) {
        Ork[] orks = new Ork[5];
        Dagger[] daggers = new Dagger[orks.length];

        for (int i = 0; i < daggers.length; i++) {
            daggers[i] = new Dagger();
        }

        for (int i = 0; i < orks.length; i++) {
            Dagger leftFork = daggers[i];
            Dagger rightFork = daggers[(i + 1) % daggers.length];

            orks[i] = new Ork(leftFork, rightFork);

            Thread t = new Thread(orks[i], "Ork " + (i + 1));
            t.start();
        }
    }
}
