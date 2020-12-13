package at.smartsced.demos.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        Runnable r = new Runnable() {
            int x = 0, y = 2;
            boolean flag = true;

            public void run() {
                while (flag) {
                    lock.lock();
                    try {
                        x = x + 2;
                        y++;
                    }finally{
                        lock.unlock();
                    }
                    if (x == y) {
                        System.out.println("Fertig");
                        flag = false;
                    }
                }
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
