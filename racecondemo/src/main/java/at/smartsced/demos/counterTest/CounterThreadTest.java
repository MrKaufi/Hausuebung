package at.smartsced.demos.counterTest;

class CounterThreadTest {
    public static void main(String[] args) {
        CounterSynchronized obj = new CounterSynchronized(3);

        CounterThread ct1 = new CounterThread(obj);
        CounterThread ct2 = new CounterThread(obj);

        ct1.start();
        ct2.start();
    }
}
