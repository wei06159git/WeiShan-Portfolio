import java.util.ArrayList;
import java.util.List;

public class TraceInfection {
	
	public static List<String> virusTrace(int x,int y,List<Triple> list1, List<String> list2, int loop, pcTag target){
		
		for (int i=0; i < list1.size(); i++){     // m Triple elements
			if(list1.get(i).time >=x && list1.get(i).time <=y){
				if(list1.get(i).computer1.text == "c1" || list1.get(i).computer2.text == "c1" || list1.get(i).computer1.tag == 1 || list1.get(i).computer2.tag ==1){
					list1.get(i).computer1.tag = 1;
					list1.get(i).computer2.tag = 1;
				}
			}
			
			if(list1.get(i).computer1.tag == 1 && list1.get(i).computer1.use ==0){
				
				list2.add(list1.get(i).computer1.text);
				list1.get(i).computer1.use = 1; // use = "1" means it has been added to infection List
			}
			
			if(list1.get(i).computer2.tag == 1 && list1.get(i).computer2.use ==0){
				
				list2.add(list1.get(i).computer2.text);
				list1.get(i).computer2.use = 1; // use = "1" means it has been added to infection List
			}
			
			loop++;
			System.out.println("loops: " + loop);
		}
		
		if (target.tag == 1){
			System.out.println(target.text+" is possible to get infected by the time " + y);
		}
		else{
			System.out.println(target.text+" is NOT possible to get infected by the time " + y);
		}
		return list2;
	}

	public static void main(String[] args){
		
		//Assume c1 get infected at time 2
		
		//Create ArrayList of "Triple"
		List<Triple> traceData = new ArrayList<>();
		//Create ArrayList for infectionList
		List<String> infectionList = new ArrayList<>();
		
		pcTag c1 = new  pcTag("c1");
		pcTag c2 = new  pcTag("c2");
		pcTag c3 = new  pcTag("c3");
		pcTag c4 = new  pcTag("c4");
		pcTag c5 = new  pcTag("c5");
		pcTag c6 = new  pcTag("c6");
		pcTag c7 = new  pcTag("c7");
		pcTag c8 = new  pcTag("c8");
		pcTag c9 = new  pcTag("c9");
		pcTag c10 = new  pcTag("c10");
		
		System.out.println("Infected Computer is " + c1.text);
		System.out.println("Target Computer is " + c7.text);
		
	
		traceData.add(new Triple(c2, c3, 4));
		traceData.add(new Triple(c2, c4, 8));
		traceData.add(new Triple(c1, c5, 9));
		traceData.add(new Triple(c6, c5, 10));
		traceData.add(new Triple(c1, c2, 10));
		traceData.add(new Triple(c7, c4, 12));
		traceData.add(new Triple(c1, c8, 12));
		traceData.add(new Triple(c2, c9, 12));
		traceData.add(new Triple(c10, c7, 12));
		traceData.add(new Triple(c3, c8, 12));
		
		System.out.println("Total of inputs: " + traceData.size());
		
		int loop =0;
		
		//start time
		int x = 2;
		//end time
		int y = 12;
		
		
		
		virusTrace(x,y,traceData,infectionList, loop, c7);
		
		System.out.println("List of Infected Computer: ");
		for(int i=0;i<infectionList.size();i++){
			System.out.print(infectionList.get(i) + " ");
		}
		
	}// end main

}//end class
 