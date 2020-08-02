import java.util.ArrayList;
import java.util.List;

public class VirusTrace{
	
	public static DisjointSet findSet(DisjointSet x){
		if (x != x.parent){
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}
	
	public static void link(DisjointSet x, DisjointSet y){
	
		if (x.rank > y.rank){
			y.parent = x;

		}
		else {
			x.parent = y;
			if (x.rank == y.rank){
				y.rank = y.rank +1;
			}
		}	
	}
	
	public static void union(DisjointSet x, DisjointSet y){
		link(findSet(x), findSet(y));
	}
	
	public static List<String> virusTrace(int start,int end,List<TripleDisjointSet> list1, List<DisjointSet> pcList){
		List<String> list2 = new ArrayList<>();
		
		int dummy = 0;
		//When time is sorted
		for (int i=0; i < list1.size(); i++){
			
			if(list1.get(i).computer1.text =="c1" || list1.get(i).computer2.text =="c1"){
				dummy = 1;
			}
			if(list1.get(i).time >=start && list1.get(i).time <=end && dummy > 0){
				union(list1.get(i).computer1, list1.get(i).computer2);
			}
		}
		
		for(int j=0; j<pcList.size();j++){
			//check if it is in same set as infected c1 
			if (findSet(pcList.get(0)).text == findSet(pcList.get(j)).text){   //[0] is the infected one
				list2.add(pcList.get(j).text);
			}
		}
		
		
		return list2;
	}
	
	public static void main(String[] args){
		
		int startTime = 2;
		int endTime = 12;
		
		DisjointSet c1=new DisjointSet("c1");
		c1.MakeSet(c1);
		DisjointSet c2=new DisjointSet("c2");
		c2.MakeSet(c2);
		DisjointSet c3=new DisjointSet("c3");
		c3.MakeSet(c3);
		DisjointSet c4=new DisjointSet("c4");
		c3.MakeSet(c4);
		DisjointSet c5=new DisjointSet("c5");
		c3.MakeSet(c5);
		DisjointSet c6=new DisjointSet("c6");
		c3.MakeSet(c6);
		DisjointSet c7=new DisjointSet("c7");
		c3.MakeSet(c7);
		
		//create ArrayList of "DisjointSet"
		//Assume "c1" is infected and its position is in [0] of the pcList 
		List<DisjointSet> pcList = new ArrayList<>();
		pcList.add(c1);
		pcList.add(c2);
		pcList.add(c3);
		pcList.add(c4);
		pcList.add(c5);
		pcList.add(c6);
		pcList.add(c7);
		
		System.out.println("List of Computers: ");
		for(int i=0;i<pcList.size();i++){
			System.out.print(pcList.get(i).text + " ");
		}
		
		//Create ArrayList of "Triple"
		List<TripleDisjointSet> traceData = new ArrayList<>();

		traceData.add(new TripleDisjointSet(c2,c3,4));
		traceData.add(new TripleDisjointSet(c1,c4,8));
		traceData.add(new TripleDisjointSet(c1,c5,9));
		traceData.add(new TripleDisjointSet(c2,c5,10));
		traceData.add(new TripleDisjointSet(c3,c6,10));
		traceData.add(new TripleDisjointSet(c7,c4,12));
		
		//Create ArrayList for infectionList
		List<String> infectionList = virusTrace(startTime,endTime,traceData, pcList);
		System.out.println();
		System.out.println("List of Infected Computer: ");
		for(int i=0;i<infectionList.size();i++){
			System.out.print(infectionList.get(i) + " ");
		}
		
	} // end main
} //end class