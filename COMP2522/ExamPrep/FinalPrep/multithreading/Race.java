class Counter {
    int count;
    // Synchronized keyword: only one at a time for the same object
    public synchronized void increment() {
        count++;
    }
    public synchronized void decrement() {
        count--;
    }
}

public class Race {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Runnable obj1 = () -> {
            for (int i = 1; i <= 1000; i++) c.increment();
        };

        Runnable obj2 = () -> {
            for (int i = 1; i <= 1000; i++) c.decrement();
        };

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c.count);
    }
}