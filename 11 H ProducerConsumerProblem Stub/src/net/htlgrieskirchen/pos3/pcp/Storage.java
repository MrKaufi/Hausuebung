/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage {

    private final ArrayBlockingQueue<Integer> queue;

    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;

    public Storage() {
        this.fetchedCounter = 0;
        this.storedCounter = 0;
        this.underflowCounter = 0;
        this.overflowCounter = 0;
        this.productionComplete = false;
        queue = new ArrayBlockingQueue<>(10);
    }

    public synchronized boolean put(Integer data) throws InterruptedException {
        // implement this
        
        if (queue.offer(data)) {
            storedCounter++;
            return true;
        } else {
            overflowCounter++;
            return false;
        }

    }

    public synchronized Integer get() {
        // implement this
        Integer erg = queue.poll();
        if (erg == null) {
            underflowCounter++;
            return erg;
        }
        else{
            fetchedCounter++;
            return erg;
        }
        
    }

    public boolean isProductionComplete() {
        // implement this
        return productionComplete;
    }

    public void setProductionComplete() {
        // implement this
        this.productionComplete = true;
    }

    public int getFetchedCounter() {
        // implement this
        return fetchedCounter;
    }

    public int getStoredCounter() {
        // implement this
        return storedCounter;
    }

    public int getUnderflowCounter() {
        // implement this
        return underflowCounter;
    }

    public int getOverflowCounter() {
        // implement this
        return overflowCounter;
    }
}
