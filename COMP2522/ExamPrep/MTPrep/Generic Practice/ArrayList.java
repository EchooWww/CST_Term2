//Make this class GENERIC
//Test by creating an ArrayList for strings and then add strings and call the display

public class ArrayList <T> {
	// Don't need to add <T> to the methods in this class
	private T[] list;
	private int pos;
	private int size = 10;
	public ArrayList(){
//		list = new T[size];
		list = (T[])new Object[size];
	}
	public boolean add(T a){
		boolean added = false;
		if (pos < size){
			list[pos++] = a;
			added = true;
		}
		return added;
	}
	public T getNext(){
		try {
			T result = list[--pos];
			return result;
		} catch (IndexOutOfBoundsException e) {
			System.err("No next element in the list!");
		}

	}
	public void display(){
		for (int i=0; i<pos; i++)
			System.out.print(""+list[i]+" ");
		System.out.println("");
	}

	public static void main(String[] args){
		ArrayList<Integer> l = new ArrayList()<Integer>;
		for (int j=5; j<=14; j++)
			l.add(j);
		l.display();
		System.out.println("GETNEXT TEST");
		for (int j=0; j<10; j++)
			System.out.print(""+l.getNext()+" ");
	}
}
		
			