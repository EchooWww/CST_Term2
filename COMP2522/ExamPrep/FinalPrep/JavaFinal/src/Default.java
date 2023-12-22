interface A {
    default void foo() {
        System.out.println("A");
    }
}
interface B{
    default void foo() {
        System.out.println("B");
    }
}

public class Default implements A, B {
    public static void main(String[] args) {
        // what if we implement both A and B and both have defined foo()?
        // we have to provide implementation of foo() in Default class
        new Default().foo();
    }
    @Override
    public void foo() {
        System.out.println("C");
    }
}
