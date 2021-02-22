package at.smartsced.demos;

public class DemoProg {
    static int counter = 0;
    public static class CounterThreadA extends Thread {
        public void run() {
            counter = 10;
            counter++; counter++;
            System.out.println("A-Counter: " + counter);
        }
    }
    public static class CounterThreadB extends Thread {
        public void run() {
            counter = 20;
            counter++;counter++;counter++;counter++;
            counter++;counter++;
            System.out.println("B-Counter: " + counter);
        }
    }
    public static void main(String[] args) {
        Thread a = new CounterThreadA();
        Thread b = new CounterThreadB();
        a.start(); b.start();
    }
}