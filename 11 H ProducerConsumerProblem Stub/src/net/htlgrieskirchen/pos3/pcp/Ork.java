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
public class Ork implements Runnable {

    Dagger leftDagger;
    Dagger rightDagger;

    public Ork(Dagger leftDagger, Dagger rightDagger) {
        this.leftDagger = leftDagger;
        this.rightDagger = rightDagger;
    }

    public void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // drinking
                doAction(System.nanoTime() + ": drinking");
                synchronized (leftDagger) {
                    doAction(System.nanoTime() + ": Picked up left dagger");
                    synchronized (rightDagger) {
                        // feasting
                        doAction(System.nanoTime() + ": Picked up right dagger - feasting");
                        doAction(System.nanoTime() + ": Put down right dagger");
                    }
                    // Back to drinking
                    doAction(System.nanoTime() + ": Put down left dagger. Back to drinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

}
