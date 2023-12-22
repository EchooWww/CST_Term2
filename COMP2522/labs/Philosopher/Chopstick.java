//: concurrency/Chopstick.java
// Chopsticks for dining philosophers.

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Chopstick {
  private ReentrantLock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();
  private boolean taken = false;
  public synchronized
  void take() throws InterruptedException {
    lock.lock();
    try {
      while(taken)
        condition.await();
      taken = true;
    } finally {
      lock.unlock();
    }
  }
  public synchronized void drop() {
    lock.lock();
    try {
      taken = false;
      notifyAll();
    } finally {
      lock.unlock();
    }
  }
} ///:~
