import java.util.*;

public class TwoDMapExample {
	public static void main(String[] args){
		Map<String, Map<String, String>> student = new HashMap<>();
		Map<String, String> BCMap = new HashMap<>();
		Map<String, String> ONMap = new HashMap<>();
		BCMap.put("Vancouver", "A00012345");
		BCMap.put("New West", "A00067891");
		ONMap.put("Toronto", "A00038234");
		ONMap.put("Ottawa","A00000123");
		student.put("BC", BCMap);
		student.put("ON", ONMap);



		//create student 2D Map [Province, Map[City, Id]]
		//create students in BC Map
		//"Vancouver", "A00012345"
		//"New West", "A00067891"
		//store in BC location
		//create students in ON Map, I reused same object reference
		//"Toronto", "A00038234"
		//"Ottawa","A00000123"
		//store in ON location
		System.out.println("student prov = BC "+student.get("BC"));
		System.out.println("student prov = ON "+student.get("ON"));
	}
}

