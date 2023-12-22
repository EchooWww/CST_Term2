//Make GENERIC
//Test with an array of strings

public class MinMax <T extends Comparable<T>>{

	public static T min(T[]data){
		T result = data[0];
		for (T value : data){
			if (result.compareTo(value) > 0)
				result = value;
		}
		return result;
	}
	public static void main(String[] args){
		int test[] = {7,3,8,12,1,5};
		System.out.println("smallest = "+min(test));
	}
}
