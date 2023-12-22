public class Main {
    public static void main(String[] args) {
//        MyInterface myInterface = new MyInterface() {
//            @Override
//            public void message() {
//                System.out.println("Hello World!");
//            }
//        }
        String name = "Echo";
        char symbol = '!';
        MyInterface myInterface = (s, c) ->{
            System.out.println("Hello World" + c);
            System.out.println("It is a nice day, "+ s + c);
        };
        MyInterface myInterface2 = (x, y) -> System.out.println("Hello, " + x + y);
        myInterface.message(name, symbol);
        myInterface2.message(name, symbol);
    }
}