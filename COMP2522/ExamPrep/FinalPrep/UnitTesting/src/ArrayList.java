//Purpose: Building test cases
//Description: create the test cases as outlined below. The purpose is NOT to find and
//				fix problems but to create test cases that COULD find problems. You are
//				not to fix any failed tests but you must make valid and correct JUnit tests
//There are 19 tests required. 0.5 marks each, all tests completed = extra 0.5 total 10 marks
//Private methods in this case do not need direct testing but instead are part of their public
//method they support.

public class ArrayList {
	Object[] array;
	int pos = 0;
	public ArrayList() {
		array = new Object[10];
	}
	//constructor Test: -ve size test
	public ArrayList(int size) {
		array = new Object[size];
	}
	//size Tests: default size is correct, after add size check, after adding more than current size check
	public int size() {return array.length;}
	//add Tests: adding a single object, adding more than current size
	public boolean add(Object o) {
		if (pos < array.length)
			array[pos++] = o;
			else
				add(o,10);
		return true;
	}
	//add Tests: see above - helper function with public add
	private boolean add(Object o, int size) {
		Object[] temp = new Object[array.length+size];
		System.arraycopy(array, 0, temp, 0, array.length);
		temp[pos++] = o;
		array = temp;
		return true;
	}
	//getAt tests: -ve location, beyond size location, valid location, end of list, start of list
	public Object getAt(int x) {
		if (x < array.length)
			return array[x];
		else
			return null;
	}
	//deleteAt Tests: -ve location, beyond size location, valid location, end of list, start of list, null list
	public Object deleteAt(int x){
			Object temp = getAt(x);
			if (temp != null) 
				removeAt(x);
			return temp;
	}
	//removeAt Tests: helper function to deleteAt
	private void removeAt(int x){
		Object[] temp = new Object[array.length-1];
		System.arraycopy(array,0,temp,0,x);
		System.arraycopy(array,x+1,temp,x,array.length-x-1);
		pos--;
		array = temp;
	}
	//print Tests: empty list, full list
	public void print(){
		for(int i=0;i<pos;i++)
			System.out.print(""+array[i]+" ");
	}
//	public static void main(String[] args) {
//		ArrayList list = new ArrayList();
//		list.add(5);
//		list.add(1);
//		list.add(8);
//		list.add(12);
//		list.add(25);
//		System.out.println(""+list.getAt(0));
//		System.out.println("deleted ="+list.deleteAt(3));
//		list.print();
//		ArrayList l = new ArrayList(0);
//		System.out.println(l.size());
//	}

}
