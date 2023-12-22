public class AnonyymousInnerClasses {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.makeNoise();

        Animal bigfoot = new Animal() {
            @Override
            public void makeNoise() {
                System.out.println("Grawlneenlnenlengehehe");
            }
        };
        bigfoot.makeNoise();

//        Runnable myAnonymous =()->{
//            System.out.println("Hello World!");
//        };
        Runnable myAnonymous = new Runnable(){
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        Thread myThread = new Thread(myAnonymous);
        myThread.start();
    }
}
