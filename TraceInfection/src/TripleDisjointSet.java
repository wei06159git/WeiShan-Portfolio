
public class TripleDisjointSet {
	public DisjointSet computer1;
	public DisjointSet computer2;
	public int time;
	
	public TripleDisjointSet(DisjointSet c1, DisjointSet c2, int time){
		this.computer1 = c1;
		this.computer2 = c2;
		this.time = time;
	}
}
