public class Multithread2 implements Runnable{
    int threadNumber;
    public Multithread2(int num) {
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
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 4; i++) {
            Multithread2 myThing = new Multithread2(i);
            Thread myThread = new Thread(myThing);
            myThread.start();
//            myThread.join();
//            System.out.println( myThread.isAlive());
        }
        // main thread exception: won't affect other threads we started.
//        throw new RuntimeException();
    }
}
