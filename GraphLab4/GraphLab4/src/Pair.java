public class Pair{
	private final int startVertexNum, endVertexNum;
	
	public Pair(int startVertexNum,int endVertexNum){
		this.startVertexNum = startVertexNum;
		this.endVertexNum = endVertexNum;
	}
	
	public int getStartVertexNum() {
		return startVertexNum;
	}

	public int getEndVertexNum() {
		return endVertexNum;
	}

	public boolean equals(Pair other){
		return (this.getStartVertexNum() == other.getStartVertexNum()) && 
				(this.getEndVertexNum() == other.getEndVertexNum());
	}
	
	public String toString(){
		return "(" + this.startVertexNum + ", " + this.endVertexNum + ")";
	}
	
	
}
