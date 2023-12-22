
public class MultithreadThing extends Thread {
    int threadNumber;
    public MultithreadThing(int num) {
        threadNumber = num;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i+ " from thread " + threadNumber);
//            if (threadNumber == 3) throw new RuntimeException();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 4; i++) {
            MultithreadThing myThing = new MultithreadThing(i);
            myThing.start();
        }
        // main thread exception: won't affect other threads we started.
//        throw new RuntimeException();
    }
}
