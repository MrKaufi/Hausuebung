package at.smartsced.demos.ReentrantDemo;

import java.util.concurrent.locks.ReentrantLock;

class Display implements Runnable {
    private String threadName;
    ReentrantLock lock;

    Display(String threadName, ReentrantLock lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    public void run() {
        System.out.println("In Display run method, thread " + threadName +
                           " is waiting to get lock");
        //acquiring lock
        lock.lock();
        try {
            System.out.println("Thread " + threadName + " has got lock");
            methodA();
        } finally {
            lock.unlock();
        }
    }

    public void methodA(){
        System.out.println("In Display methodA, thread " + threadName
                           + " is waiting to get lock");
        //acquiring lock
        lock.lock();
        try {
            System.out.println("Thread " + threadName + "has got lock");
            System.out.println("Count of locks held by thread " + threadName
                               + " - " + lock.getHoldCount());
        } finally{
            lock.unlock();
        }
    }
}
