class Student{
	String name;
	public Student(String n){name = n;}
}
class Admin {
	Student[] students;
	int pos = 0;
	public Admin(){
		students = new Student[10];
	}
	public void add(Student s){
		if (pos <10)
			students[pos++] = s;
	}
	public void display(){
		for (int i=0; i<10; i++)
			System.out.println(students[i].name);
	}
}//DEFINE interface SCHOOL TO HAVE AN Admin class 
interface School {
	Admin getAdmin();
	void add(Student s);
	Student get(int x);
	// admin class
	static class Admin {
		Student[] students;
		int pos = 0;
		public Admin(){
			students = new Student[10];
		}
		public void add(Student s){
			if (pos <10)
				students[pos++] = s;
		}
		public void display(){
			for (int i=0; i<10; i++)
				System.out.println(students[i].name);
		}
	}
}
public class BCIT implements School{
	// constructor
	public BCIT(){
		Admin a = new Admin();
		
	}
	public static void main(String[] args){//Don't change
		School s = new BCIT();
		s.add(new Student("Smith"));
		s.add(new Student("Sue"));
		Student b = s.get(0);
		System.out.println(b.name);
		School.Admin a = s.getAdmin();
		a.display();
		// using Admin inside School
		School.Admin a2 = new School.Admin();

	}
}
		