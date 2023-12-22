# COMP 2522: Object-Oriented Programming

## Lecture 2 (Sep 13th, 2023)

### Final

- Final reference: you can't point to another object
- Final arguments: you can make arguments final using `final` keyword in the argument list. If we try to reassign the argument, we get a compile time syntax error.
- Final methods prevent overriding. If we try to override a final method, we get a compile time syntax error. Final methods are more efficient because it becomes inline, so no stack is required.

### Inheritance and Polymporphism

- If a variable is first declared as the super class, and then instantiated as the subclass, the variable will only have access to the methods and variables of the super class: But if there's methods in the subclass that override the methods in the super class, then the methods in the subclass will be called instead.

- If a variable is first declared as the subclass, and then instantiated as the subclass, we will need to cast it to the subclass when assigning it to the variable.

```java
Animal a = new Animal();
a = new Dog(); // OK
a.bark(); // Compile time error
a.eat(); // OK, and will call eat() in Dog class. coz eat() is in both Animal and Dog, and is overridden in Dog
```

### Abstract Classes

🆚 Abstract classes vs Interfaces
Abstract classes are similar to interfaces:

- We cannot instantiate them, but we can create object references
- They may contain a mix of methods declared with or without an implementation

```java
// if Animal is an abstract class
Animal a = new Animal();// Compile time error
Animal a = new Dog(); // OK, coz Dog is not abstract. We can still create object references for abstract classes Animal, just can't instantiate them
```

However, they are different in some ways:

- Abstract classes can have non-static and non-final variables
- Abstract classes can have constructor methods
- Abstract classes can have any visibility: public, protected, private
- A class can only extend one class, including abstract class, but it can implement multiple interfaces

```java
// if we inherit from an abstract class, we must implement all abstract methods
// if we remove the move() method from Tiger, we will get a compile time error
// if we don't want to implement the abstract method, we can make the Tiger class also abstract
public class Tiger extends Animal {
    @Override
    public void move() {
        System.out.println("Tiger moves");
    }
    public void eat() {
        System.out.println("Tiger eats");
    }
}
// or
public abstract class Tiger extends Animal {
    public void eat() {
        System.out.println("Tiger eats");
    }
}
```

### Interfaces

Interfaces are "more abstract" than abstract classes:

- Interfaces may contain none definition methods
- Interfaces may contain data members, but they are all implicitly static and final

If a class implements an interface, we can use the object of that class to call the methods in the interface, and if the class overrides the methods in the interface, the methods in the class will be called instead.

```java
public interface Car {
    public void drive();
}

public interface Boat {
    public void sail();
}

public class Vehicle {
    public void move(){
        System.out.println("Vehicle moves");
    }
}

public class Hovercraft extends Vehicle implements Car, Boat{
    @Override
    public void drive(){
        System.out.println("Hovercraft drives");
    }
    @Override
    public void stop(){
        System.out.println("Hovercraft stops");
    }
    @Override
    public void sail(){
        System.out.println("Hovercraft sails");
    }
    @Override
    public void dock(){
        System.out.println("Hovercraft docks");
    }
}

public class Transportation() {
  public void testDrive(Car c){
    c.drive();
  }
  public void testSail(Boat b){
    b.sail();
  }
  public void testTurn(Vehicle v){
    v.turn();
  }
}

public class Main {
    public static void main(String[] args) {
      // we can use h as a Car, Boat, or Vehicle
        Hovercraft h = new Hovercraft();
        Transportation t = new Transportation();
        t.testDrive(h); // Hovercraft drives
        t.testSail(h); // Hovercraft sails
        t.testTurn(h); // Vehicle turns
    }
}

```

### Inner Classes

- The inner class can access all members of the outer class, even the private ones
- We cannot declare the inner class object without the outer class object

```java
public class Egg {
    private Yolk y;
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }
    public Egg() {
        System.out.println("New Egg()");
        // won't compile if we don't instantiate the inner class with an outer class object
        Yolk y = new Yolk();
    }
}
```

- For example, we can make our button and action listener inner classes of our frame, so that we can access the private variables in the outer class

- Access the outer class from the non-static inner class (directly):

```java
public class OuterClass {
    private int x = 10;
    class InnerClass {
        public int myInnerMethod() {
            return x;
        }
    }
}
```

- Access the inner class from the outer class (with an object):

```java
public class OuterClass {
    int x = 10;
    class InnerClass {
        int y = 5;
    }
    InnerClass myInner = new InnerClass();
    x = x + myInner.y;
}
```

- Access outer class from a **static** inner class (with an object)

```java

public class OuterClass {
    int x = 10;
    static class InnerClass {
        int y = 5;
        y = y + x; // Compile time error
        OuterClass oc = new OuterClass();
        y = y + oc.x; // OK, when the inner class is static, we can access the outer class with an object
    }

}
```

### Access Modifiers for Nested Classes

The access modifier always means accessibility from outside the class, inner classes can access all members of the outer class, even the private ones.

- private: only accessible within the class
- package(default): only accessible within the package
- protected: accessible within the package and subclasses
- public: accessible everywhere

```java
public class Egg{
    private int x;
    class Yolk{
        public int getX(){
            return x;
        }
    }
    public static void main(String[] args){
        Egg e = new Egg();
        // it needs to be explicitly instantiated with an outer class object
        Yolk y = e.new Yolk();
        System.out.println(y.getX());
    }
}
```

- After we compile the above code, we will get two class files: Egg.class and Egg$Yolk.class

### the MyWindow class

#### The super() constructor

- When we create a new MyWindow object, it will call the constructor of JFrame, and JFrame will create a window with the title "Hello world"
- We're doing this explicitly because we want to call the exact constructor that accepts a string argument
- Even if we don't call it explicitly, it will still be called by default when we create a new MyWindow object, just with no arguments: Every constructor must call the constructor in its super class

#### The listeners

- We also add a window listener to the window, so that when we click the close button, the window will close
  - Listeners are all interfaces, so we cannot instantiate them directly, and we need to provide definitions for all methods in the interface, or that would become an abstract class
  - We can use anonymous inner classes to implement the interface, and provide the definitions for the methods
  - In our case, the `WindowAdapter` class implements the `WindowListener` interface, and we only need to override the `windowClosing()` method by providing our own definition in the anonymous inner class

```java
public class MyWindow extends JFrame{
	public MyWindow(){
		// calls the JFrame constructor with "Hello world"
		super("hello world");

        button = new JButton("Click me");

        add(button, BorderLayout.NORTH);
        // Add a window listener to the window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
			System.exit(0);
		}
		});

		setSize(350, 150);//resize the window to width=350 height=150 pixels
		setVisible(true);//make the window visible
	}
	public static void main(String[] args){
		new MyWindow();
        // JFrame() is called by default
	}
}
```

### Polymporphism and constructors

If we have a class hierarchy, and we want to create an object of the subclass, the constructor of the superclass will be called first, and then the constructor of the subclass will be called. So if we have a class hierarchy like this:

```java
public class Animal {
    public Animal() {
        System.out.println("Animal constructor");
    }
}

public class Dog extends Animal {
    public Dog() {
        System.out.println("Dog constructor");
    }
}
```

When we create a new Dog object, the output will be:

```bash
Animal constructor
Dog constructor
```

### The execution of Beetle.java

The Beetle class is a good example of how the constructors are called in a class hierarchy. The code is:

```java
class Insect {
  int i = 9;
  int j;
  Insect() {
    prt("i = " + i + ", j = " + j); // 4th line, constructor of super class, and will print i = 9, j = 0
    j = 39;
  }
  static int x1 =
    prt("static Insect.x1 initialized"); // 1st line, static variable in super class, will be loaded first
  static int prt(String s) {
    System.out.println(s);
    return 47;
  }
}

public class Beetle extends Insect {
  Beetle() {
    prt("k = " + k); // 6th line, constructor of sub class, and will print k = 47
    prt("j = " + j); // 7th line, constructor of sub class, and will print j = 39
  }
  int k = prt("Beetle.k initialized"); // 5th line, instance variables will be compiled as the first part of the constructor
  static int x2 =
    prt("static Beetle.x2 initialized"); // 2nd line, static variable in sub class, will be loaded second
  public static void main(String[] args) {
    System.out.println("Started program"); // 3rd line, main method
    Beetle b = new Beetle();
  }
}
```

Output:

```bash
static Insect.x1 initialized
static Beetle.x2 initialized
Started program
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
```

## Exceptions

### `throw` keyword

With the `throw` keyword, we can throw an exception manually, explicitly, usually not standard exceptions, but exceptions that we define ourselves.

### `throws` keyword

The `throws` keyword is used in the method declaration to indicate that the method may throw an exception. If any execption occurs and it matches the type of exception listed in the `throws` clause, the exception will be thrown to the caller of the method automatically, we don't need to use the `throw` keyword. It's usually used for standard exceptions.

```java
class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}
class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}

public class RethrowNew {
    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown from f()");
    }
    public static void main(String[] args) {
        try {
            f();
        } catch (OneException e) {
            System.out.println("Caught in inner try, e.printStackTrace()");
            e.printStackTrace(System.out);
            throw new TwoException("from inner try");
        }
    }
}
```

```java
foo() {
    try {
    System.out.println("started foo");
    bar();
    System.out.println("bar finished");
    } catch (Exception e) {
    System.out.println("exception");
    } finally {
    System.out.println("foo finally");
    }
}

bar () throws Exception {
    if (Math.random() > 0.5) {
        throw new Exception("bar");
    }
}
```

## Generics

### Syntax

- `<>` is used to specify the type
- `T` is used to represent the type. We can use any letter to represent the type, but `T` is the most common one
- we can also specify the Type to be a subclass of a class, or implement an interface, with the `extends` keyword (for both classes and interfaces)
  ```java
  public class Box<T extends Number> {
      private T t;
      public void set(T t) {
          this.t = t;
      }
      public T get() {
          return t;
      }
  }
  ```

### Generic classes

To create a generic class, we need to use the `<>` syntax, and put the type in the `<>` when we instantiate the class. We don't need to use the `<>` in the methods of the class, because the type is already specified when we instantiate the class.

```java
public class Box<T> {
    private T t;
    public void set(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }
}
```

### Generic methods

We can also create generic methods in a non-generic class, and we need to use the `<>` syntax in the method declaration, and put the type in the `<>` when we call the method.

```java
public class Box {
    public <T> void print(T t) {
        System.out.println(t);
    }
}
```

### wildcards in generic types

#### Upper bounded wildcards

- We can use `? extends` to specify that the type must be a subclass of a class, or implement an interface. The question mark `?` is called the wildcard, and it means that the type can be any type, as long as it's a subclass of the specified class, or implements the specified interface.

```java
public class Box {
    public static void printList(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println(n);
        }
    }
}
```

#### Lower bounded wildcards

We can also specify the lower bound of the type, with `? super`. For example, we can use `List<? super Integer>` to represent a list of any type that is a superclass of Integer, such as `List<Number>`, `List<Object>`, etc.

```java
public class Box {
    public static void printList(List<? super Integer> list) {
        for (Object n : list) {
            System.out.println(n);
        }
    }
}
```

💡 Why use wildcards?
