
public class DisjointSet {
	public DisjointSet parent; 
	public int rank;
	public String text;
	
	public DisjointSet(String text){
		this.parent = null;
		this.rank = 0;
		this.text = text;
	}
	
	public DisjointSet(int rank){
		this.parent = null;
		this.rank = rank;
	}
	
	public void MakeSet(DisjointSet x){
		x.parent = x;
		x.rank =0;
	}
	
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public void setParent(DisjointSet root){
		this.parent = root;
	}
	
}
