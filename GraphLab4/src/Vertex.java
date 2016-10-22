import java.io.Serializable;

public class Vertex implements Serializable{

	private static final long serialVersionUID = 296528301902940244L;
	private final int id;        // id of Vertex is Integer Value from 0 to (Vertices - 1)
	private boolean marked;      // marked attribute tells whether vertex has been marked or not
	                             // This for marking vertices to implement BFS
	
	public Vertex(int num){
		this.id = num;          
		this.marked = false;
	}

	public int getId(){ return this.id; }
	public boolean isMarked() { return this.marked; }
	
	public void mark(){ this.marked = true; }	
	public void unMark(){ this.marked = false; }

}
