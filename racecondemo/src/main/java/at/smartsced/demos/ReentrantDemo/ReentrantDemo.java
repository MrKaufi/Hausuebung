package at.smartsced.demos.ReentrantDemo;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDemo {
    public static void main(String[] args) {
        ReentrantLock rLock = new ReentrantLock();
        Thread t1 = new Thread(new Display("Thread-1", rLock));
        Thread t2 = new Thread(new Display("Thread-2", rLock));
        System.out.println("starting threads ");
        t1.start();
        t2.start();
    }
}