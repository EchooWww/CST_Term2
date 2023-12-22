/** In-lab assignment: Threading
1. Create 2 threads to add the data provided in main <1 mark>
2. After the threads have FINISHED print the resulting linked list (in-order) <1 mark>
3. Exacerbate any existing race conditions <1 mark>
4. Give TWO locations where a race condition can occur <2 marks>
********************************************************************************************/

interface DIterator<T> {
	boolean isEmpty();
	boolean hasNext();
	boolean hasPrevious();
	T next();
	T previous();
}
class DLinkedList <T extends Comparable<T>>{
	private DNode head;
	private class DNode {
		T data;
		DNode previous, next;
		DNode(T d){
			data = d;
		}
	}
	public void clear(){
		head = null;
	}

	// Put the synchnronized keyword here
	public synchronized boolean insert(T d) {
		try {
		DNode temp = new DNode(d);
		DNode cur = head;
		DNode prev = head;
		//1. empty list case
		if (head == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			head = temp;
			return true;
		}
		//2. non-empty list, find position
		while((cur.next != null) && (cur.data.compareTo(d)<0)){
			prev = cur;
			cur = cur.next;
		}
		//3. value exists in list already - fail
		if (cur.data.compareTo(d) == 0 )
			return false;
		//4. single node in list
		if (cur == prev){
			//5. single node < new node
			if (cur.data.compareTo(d) < 0){
				cur.next = temp;
				temp.previous = cur;
				return true;
			}
			//6. single node > new node
			temp.next = cur;
			cur.previous = temp;
			head = temp;
			return true;
		}
		//7. multiple nodes in list
		prev.next = temp;
		temp.next = cur;
		//8. check if being added at the start of the list
		// if it is there is no previous node and the head of list
		// needs to change
		if (cur.previous != null)
			cur.previous = temp;
		else
			head = temp;
		temp.previous = prev;
		}catch(Exception e){}
		return true;
	}
	public DIterator<T> iterator(){
		return new DIterator<T>(){
			DNode cur = head;
			public boolean isEmpty(){
				if (cur != null)
					return false;
				return true;
			}
			public boolean hasNext(){
				return cur.next != null;
			}
			public boolean hasPrevious(){
				return cur.previous != null;
			}
			public T next(){
				T d = cur.data;
				cur = cur.next;
				return d;
			}
			public T previous(){
				T d = cur.data;
				cur = cur.previous;
				return d;
			}
		};
	}
}

//CREATE YOUR RUNNABLE CLASS(ES) HERE FOR THREADING
class Race implements Runnable {
    int[] list;
    @Override
    public void run() {
        for(int n: list) {
						RaceTest.list.insert(n);
				}
    }
}

public class RaceTest {
		static DLinkedList<Integer> list = new DLinkedList<Integer>();

	public static void main(String[] args) throws Exception{
		Race obj1 = new Race();
		int[] prime1 = {47,13,23,17};//for Thread1
		obj1.list = prime1;
		Race obj2 = new Race();
		int[] prime2 = {5,19,37,7};//for Thread2
		obj2.list = prime2;
		
		//make threads and launch them
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
		//make sure you WAIT for Thread1 and Thread2 to complete before attempting to print
		t1.join();
		t2.join();
		print(list);//result should display missing data, data out of order, different each time
	}
	public static <P extends Comparable<P>> void print(DLinkedList<P> list){
		DIterator<P> i = list.iterator();
		while(!i.isEmpty())
			System.out.print(""+i.next()+" ");
		System.out.println("");
	}
	public static <P extends Comparable<P>> void printR(DLinkedList<P> list){
		DIterator<P> i = list.iterator();
		while(i.hasNext()) i.next();
		while (!i.isEmpty())
			System.out.print(""+i.previous()+" ");
		System.out.println("");
	}
}

// Where race condition can occur:
// 1. In the insert method, when the list is empty, and two threads are trying to insert at the same time, we're resetting the head twice, and losing the first value.
// 2. In the insert method, when the list is not empty, and two threads are trying to insert at the same time, we're not checking if the value already exists in the list: can cause duplicates.
// 3. In the insert method, when the list is not empty, and two threads are trying to insert at the same time, we can lose one of the nodes.