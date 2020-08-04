
public class pcTag {
	public String text;
	public int tag; //infected or not
	public int use; //check if it is already added into infection List
	
	public pcTag(String text){
		this.text = text;
		this.tag = 0;
		this.use = 0;
	}
}
