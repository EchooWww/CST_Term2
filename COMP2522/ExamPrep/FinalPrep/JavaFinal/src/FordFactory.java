import java.lang.Thread;

// Car interface
interface Car {
  boolean drive();
  void turn();
  // construct interface
  public static class Construct {
    void buildEngine(){
      System.out.println("Building engine..");
    }
    void buildBody(){
      System.out.println("Building body..");
    }
    void constructCar() {
      buildEngine();
      buildBody();
    }
  }
}

// Car factory
interface CarFactory {
  Car getCar();
}


// ModelA class
class ModelA implements Car {
  public static final int MAXDISTANCE = 3;
  private int distance = 0;
  private ModelA() {
    Construct c = new ModelAConstruct();
    c.constructCar();
  }
  public static final CarFactory factory = new CarFactory() {
    public Car getCar() {
      return new ModelA();
    }
  };

  @Override
  public boolean drive() {
    if (distance < MAXDISTANCE) {
      distance++;
      System.out.println("ModelA driven " + distance);
      return true;
    } else {
      System.out.println("ModelA has reached its max distance");
      return false;
    }
  }
  @Override
  public void turn() {
    System.out.println("Turning ModelA");
    distance = 0;
  }
  public static class ModelAConstruct extends Car.Construct {
    @Override
    public void buildEngine() {
      System.out.println("Basic engine");
    }
    @Override
    public void buildBody() {
      System.out.println("ModelA body");
    }
  }
} 

// ModelT class
class ModelT implements Car{
  public static final int MAXDISTANCE = 5;
  private int distance = 0;
  private ModelT() {
    Construct c = new ModelTConstruct();
    c.constructCar();
  }
  public static final CarFactory factory = new CarFactory() {
    public Car getCar() {
      return new ModelT();
    }
  };
  @Override
  public boolean drive() {
    if (distance < MAXDISTANCE) {
      distance++;
      System.out.println("ModelT driven " + distance);
      return true;
    } else {
      System.out.println("ModelT has reached its max distance");
      return false;
    }
  }
  @Override
  public void turn() {
    System.out.println("Turning ModelT");
    distance = 0;
  }
  
  public static class ModelTConstruct extends Car.Construct {
    @Override
    public void buildEngine() {
      System.out.println("ModelT engine");
    }
    @Override
    public void buildBody() {
      System.out.println("ModelT body");
    }
  }
}

public class FordFactory {
  public static void produce(CarFactory factory) {
    int turn = 0;
    Car c = factory.getCar();
    while(c.drive()){
      try{
        Thread.sleep(300);
      }catch(Exception e){}
      turn = (int)(Math.random()*4);
      if ( turn > 2)
        c.turn();
      }
  }
  public static void main(String[] args) {
    produce(ModelA.factory);
    produce(ModelT.factory);
  }
} 