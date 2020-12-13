package at.smartsced.demos.counterTest;

class CounterSynchronized {
    private int n = 0;
    public CounterSynchronized(int n) {
        this.n = n;
    }
    public synchronized void count() {
        for (int i = 0; i < this.n; i++) {
            System.out.println(i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}