//Add a hashCode() function to Student and add a secondHashCode() function
//See how close you can get to having all 8 students added to the array

	class Student{
		int ID;
		String name;
		public Student(String n, int id){
			name = n;
			ID = id;
		}
		public boolean equals(Object o){
			return ((Student)o).ID == ID;
		}
		public int hashCode(){
			int n = ID;
			int hashcode = 0;
			while(n > 0) {
				hashcode += n % 10;
				n = n / 10;
			}
			return hashcode % 10;
		}


		public int secondHashCode(){
			return (ID + 3) % 11;
		}

	}
public class DoubleHash {
	Student[] students;
	int size;

	public DoubleHash(int size){
		students = new Student[size];
		this.size = size;
	}
	
	private boolean add(int code, Student s){
		if ((students[code%size] == null)){
			students[code%size] = s;
			return true;
		}else
			return false;
	}

	public boolean add(Student s){
		System.out.println("adding student "+s.name);
		int pos = s.hashCode();

		if (!add(pos,s)){
			System.out.println("First collision at "+pos%size+" with "+students[pos%size].name);
			pos = s.secondHashCode();
			if (!add(pos, s)){
				System.out.println("Second collision at "+pos%size+" with "+students[pos%size].name);
				return false;
			}
		}
		return true;
	}
			
	public void display(){
		int count = 0;
		for (int i=0; i<size; i++){
			System.out.print("["+i+"] ");
			if (students[i] != null){
				count++;
				System.out.println(students[i].name);
			}
			else
				System.out.println("");
		}
		System.out.println("total = "+count);
	}
	public static void main(String[] args){
		DoubleHash d = new DoubleHash(10);
		Student s = new Student("Fred", 513);
		d.add(s);
		s = new Student("Willma", 123);
		d.add(s);
		s = new Student("Barney", 42);
		d.add(s);
		s = new Student("Betty", 647);
		d.add(s);
		s = new Student("Cain", 321);
		d.add(s);
		s = new Student("Able", 737);
		d.add(s);
		s = new Student("Maria", 359);
		d.add(s);
		s = new Student("Sue", 423);
		d.add(s);		
		d.display();
	}
}