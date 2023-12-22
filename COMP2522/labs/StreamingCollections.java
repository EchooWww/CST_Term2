import java.lang.Math;
import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.function.*;

class Transaction {
	Integer ID;
	public enum TYPE {GROCERY, MISC, APPLIANCE};
	TYPE type;
	Integer value;
	public Integer getValue(){return value;}
	public TYPE getType(){return type;}
	public Integer getId(){return ID;}
	public Transaction(int id, TYPE t, int v){ID=id;type=t;value=v;}
}
class DB{
	List<Transaction> transactions = new LinkedList<>();
	public DB(){
		for(int i=0;i<20;i++){
			int choice = (int)(Math.random()*3);
			if (choice > 2)
				transactions.add(new Transaction((int)(Math.random()*1000),Transaction.TYPE.APPLIANCE,(int)(Math.random()*100)));
			else if(choice>1)
				transactions.add(new Transaction((int)(Math.random()*1000),Transaction.TYPE.MISC,(int)(Math.random()*10)));
			else
				transactions.add(new Transaction((int)(Math.random()*1000),Transaction.TYPE.GROCERY,(int)(Math.random()*5)));
		}
	}
}
public class StreamingCollections{
	static DB db = new DB();
	//name: getGrocID()
	//params: none
	//returns: list of IDs
	
	public static List<Integer> getGrocID(){
		// //need a list to store grocery Transactions
		List<Transaction> groc = new ArrayList<>();
		// //extract groceries from the DB and add to your list
		for(Transaction t: db.transactions){
			if(t.getType() == Transaction.TYPE.GROCERY)
				groc.add(t);
		}
		
		// //sort your list based on value of grocery
		// //note that you will need a way to Compare transactions based on value
		// //Use interface Comparator. Note that Comparator is Generic
		Collections.sort(groc, new Comparator<Transaction>(){
			public int compare(Transaction t1, Transaction t2){
				return t1.getValue() - t2.getValue();
		}});
		// // we can also do this with lambda expressions
		// //Collections.sort(groc, (t1, t2) -> t1.getValue() - t2.getValue());
		
		// //need a list to store the ids of your sorted list of groceries
		// List<Integer> transactionIds = new LinkedList<>();
		// //from your list of grocery transactions add the ids to your new list
		// for(Transaction t: groc)
		// 	transactionIds.add(t.getId());
		
		// //return your list of ids
		// return transactionIds;
		List<Integer> transactionsIds = 
		db.transactions.stream()
		.filter(t->t.getType() == Transaction.TYPE.GROCERY)
		.sorted((t1,t2)->t1.getId()-t2.getId())
		.map(Transaction::getId)
		.collect(Collectors.toList());
		return transactionsIds;
	}

	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
		List<Integer> twoEvenSquares =
					numbers.parallelStream()
			.filter(n->{ System.out.println("filtering "+n);
					return n % 2 == 0;})
			.map(n-> { System.out.println("mapping " + n);
					return n * n;})
			.limit(2)
			.collect(Collectors.toList());
		System.out.println(twoEvenSquares);
	}
}
		