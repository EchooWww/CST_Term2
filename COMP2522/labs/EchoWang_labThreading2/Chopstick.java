//: concurrency/Chopstick.java
// Chopsticks for dining philosophers.

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Chopstick {
  private ReentrantLock lock = new ReentrantLock();
  private boolean taken = false;

  public synchronized boolean take() {
    if (lock.tryLock()) {
        if (!taken) {
            taken = true;
            return true;
        }
    }
    return false;
  }
  public synchronized void drop() {
    lock.unlock();
    taken = false;
    notifyAll();
  }
} ///:~
