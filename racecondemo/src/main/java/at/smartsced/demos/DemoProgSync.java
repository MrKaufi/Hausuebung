package at.smartsced.demos;

public class DemoProgSync {
    static Integer counter = 0;

    public static class CounterThreadA extends Thread {
        public void run() {
            synchronized (DemoProgSync.class) {
                counter = 10;
                counter++;
                counter++;
                System.out.println("A-Counter: " + counter);
                if(counter != 12) System.err.println("ERROR");
            }
        }
    }

    public static class CounterThreadB extends Thread {
        public void run() {
            synchronized (DemoProgSync.class) {
                counter = 20;
                counter++; counter++; counter++;
                counter++; counter++; counter++;
                System.out.println("B-Counter: " + counter);
                if(counter != 26) System.err.println("ERROR");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10000; i++) {
            Thread a = new CounterThreadA();
            Thread b = new CounterThreadB();
            a.start();
            b.start();
            a.join(); b.join();
        }
    }
}