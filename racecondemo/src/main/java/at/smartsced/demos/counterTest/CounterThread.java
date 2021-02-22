package at.smartsced.demos.counterTest;

class CounterThread extends Thread {
    CounterSynchronized counter;

    CounterThread(CounterSynchronized c) {
        this.counter = c;
    }

    @Override
    public void run()
    {
        counter.count();
    }
}
