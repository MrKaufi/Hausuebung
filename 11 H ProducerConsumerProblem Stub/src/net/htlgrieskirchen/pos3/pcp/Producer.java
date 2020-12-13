/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private final String name;
    private final Storage storage;
    private final int sleepTime;

    private final List<Integer> sent;
    private final int numberOfItems;

    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.numberOfItems = numberOfItems;
        this.sent = new ArrayList<>();
    }

    // implement this
    public List<Integer> getSent() {
        // implement this
        return this.sent;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfItems; i++) {
            do {
                try {
                    if (storage.put(i)) {
                        sent.add(i);
                    }
                    Thread.sleep(sleepTime);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!sent.contains(i));
        }
        storage.setProductionComplete();
    }
}
