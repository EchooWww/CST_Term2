//: concurrency/Philosopher.java
// A dining philosopher
import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.Print.*;

public class Philosopher implements Runnable {
  private Chopstick left;
  private Chopstick right;
  private final int id;
  private final int ponderFactor;
  private Random rand = new Random(47);
  private boolean eaten = false;

  private void pause() throws InterruptedException {
    if(ponderFactor == 0) return;
    TimeUnit.MILLISECONDS.sleep(
      rand.nextInt(ponderFactor * 250));
  }
  public Philosopher(Chopstick left, Chopstick right,
    int ident, int ponder) {
    this.left = left;
    this.right = right;
    id = ident;
    ponderFactor = ponder;
  }
  public void run() {
    try {
      while (!Thread.interrupted()) {
        System.out.println(this + " " + "thinking");
        pause();
        eaten = false;
        // Philosopher becomes hungry
        while (!eaten) {
          System.out.println(this + " " + "grabbing right");
          if (right.take()) {
            System.out.println(this + " " + "grabbing left");
            if (left.take()) {
              System.out.println(this + " " + "eating");
              pause();
              left.drop();
              right.drop();
              eaten = true;
            } else {
              right.drop();
            }
            // Wait for a random amount of time before looping back
            Thread.sleep((int)(Math.random() * 100));
          }
        }
      }
    } catch (InterruptedException e) {
      System.out.println(this + " " + "exiting via interrupt");
    }
  }

  public String toString() { return "Philosopher " + id; }
} ///:~
