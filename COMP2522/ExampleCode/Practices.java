// Practice generics
// 1. Write a min() as a generic function
// Include T extentds Comparable<T> // Even tho it's actually an interface
public static <T extends Comparable<T>> T min (T[] data) {
  T result = data[0];
  for (T value:data) {
    if (value.compareTo(result) < 0) result = value;
  return result;
}
// 2. copy(Object[] a, Object[] b){}
<T> void copy(T[] a, T[]b) {
  // source, begin, dest, begin, length
    System.arraycopy(b, 0, a, 0, a.length);
}

// 3. Generic class linkedlist

public class LinkedList<T> {
  private Node<T> head;
  class Node<T> {
    T data;
    // Be careful to specify next with the same type
    Node<T> next;
    // Constructor: shouldn't include generic
    public Node(T d) {data = d;}
  }
  public <T> void add(T d) {
    // Be careful to specify temp with the same type
    Node<T> temp = new Node(d);
    temp.next = head;
    head = temp;
  }
}

/* 
public void methodA(List<? super Integer> a);
public void methodB(List<? extends Number> b);
4. Which of the above examples allows you to add something to the list? Which allows you to get
something from the list? Explain why this makes sense.
*/ 

