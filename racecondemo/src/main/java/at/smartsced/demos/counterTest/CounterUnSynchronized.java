package at.smartsced.demos.counterTest;

class CounterUnSynchronized {
    private int n = 0;
    public CounterUnSynchronized(int n) {
        this.n = n;
    }
    public void count() {
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
