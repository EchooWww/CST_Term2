import java.util.Random;
/**
Lab Threading Part2

Purpose: to examine locks via synchronized in more detail specifically WHY
		we may wish to use different locks on different methods
		
Description: You are to fix the code to run the test twice, once with a single
			lock for both stocks and bonds, and once with different locks.
			NOTE: current code does NOT contain any locks
			NOTE: PUT YOUR LOCKS IN THE SAFEST LOCATION TO PROTECT STORES
			
Marking Guide:

Mark will be added to your Threading Part1 from last week

Function							Mark
Adding Locks CORRECTLY				1
Same lock							1
Different lock						1

**************************************************************************/

public class Synchro {
	private static Random generate = new Random();
	private static final int NSTOCKS=20;
	private static final int NBONDS = 20;
	private static final int PURCHASE = 10;
	private static int stockIndex = 0;
	private static int bondIndex = 0;
	private static int[] stocks = new int[NSTOCKS];
	private static int[] bonds = new int[NBONDS];
	private static Object stockLock;//LOCK FOR STOCKS
	private static Object bondLock;//LOCK FOR BONDS
	private static boolean done = false;
	
	static boolean stockAvailable(){
			return stockIndex < NSTOCKS;
	}
	static boolean bondAvailable(){
			return bondIndex < NBONDS;
	}
	static boolean addStock(int s){
		synchronized (stockLock){
			if (stockAvailable()){
				stocks[stockIndex] = s;
				try{
					Thread.sleep(10);
				}catch(Exception e){}
				stockIndex++;
				return true;
			}
			return false;
		}
	}
	static boolean addBond(int b){	
		synchronized (bondLock){
			if (bondAvailable()){
				bonds[bondIndex] = b;
				try{
					Thread.sleep(10);
				}catch(Exception e){}
				bondIndex++;
				return true;
			}
			return false;
		}
	}
	private static Runnable createRun(){
		return new Runnable(){
			int i=0;
			public void run(){
				while(!done){
					if (generate.nextInt(2) == 1)
						addStock(i++);
					else
						addBond(i++);
				}
			}
		};
	}
	public static void printStock(){
		System.out.println("\nstocks");
		for(int s=0; s<stocks.length; s++)
			System.out.print(stocks[s]+" ");
	}
	public static void printBond(){
		System.out.println("\nbonds");
		for(int b=0; b< bonds.length; b++)
			System.out.print(bonds[b]+" ");
	}
	public static int countBonds(){
		int result = 0;
		for(int b=0; b< bonds.length; b++)
			if (bonds[b] != 0)
				result++;
		return result;
	}
	public static int countStocks(){
		int result = 0;
		for(int s=0; s< stocks.length; s++)
			if (stocks[s] != 0)
				result++;
		return result;
	}
	public static void test(Object lock1, Object lock2){
		try {
			stockLock = lock1;
			bondLock = lock2;
			//create 4 threads and run them
			Thread t1 = new Thread(createRun());
			Thread t2 = new Thread(createRun());
			Thread t3 = new Thread(createRun());
			Thread t4 = new Thread(createRun());
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			Thread.sleep(100);//allow purchase for 100ms
			done = true;//stop threads gracefully
			printStock();
			printBond();
			System.out.println("\ntotal stocks purchased = "+countStocks());
			System.out.println("total bonds purchased = "+countBonds());
			// end the threads
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			done = false;//reset flag
		} catch(Exception e) {}
	}
	public static void main(String[] args){
		System.out.println("using same lock for stocks and bonds");
		//test using same lock for both
		Object lock = new Object();
		test(lock, lock);
		System.out.println("using different locks for stocks and bonds");
		//test using different lock for stocks and lock for bonds
		test(new Object(), new Object());
	}
}