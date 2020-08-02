import java.util.ArrayList;
import java.util.List;

public class TraceInfection {
	
	public static List<String> virusTrace(int x,int y,List<Triple> list1, List<String> list2){
		
		for (int i=0; i < list1.size(); i++){    // "m" Triple elements
			if(list1.get(i).time >=x && list1.get(i).time <=y){
				int n = list2.size();
				for(int j=0;j< n;j++){     // "n" string elements
					if (list1.get(i).computer1 == list2.get(j)){
						list2.add(list1.get(i).computer2);
					}
					else if(list1.get(i).computer2 == list2.get(j)){
						list2.add(list1.get(i).computer1);
					}
					else{
						continue;
					}
				}
			}
		}
		return list2;
	}

	public static void main(String[] args){
		
		//Assume c1 get infected at time 2
		
		//Create ArrayList of "Triple"
		List<Triple> traceData = new ArrayList<>();
		//Create ArrayList for infectionList
		List<String> infectionList = new ArrayList<>();

		traceData.add(new Triple("c2", "c3", 4));
		traceData.add(new Triple("c1", "c4", 8));
		traceData.add(new Triple("c1", "c2", 8));
		
		//start time
		int x = 2;
		//end time
		int y = 12;
		
		infectionList.add("c1");
		
		virusTrace(x,y,traceData,infectionList);
		
		System.out.println("List of Infected Computer: ");
		for(int i=0;i<infectionList.size();i++){
			System.out.print(infectionList.get(i) + " ");
		}
		
	}// end main

}//end class
 