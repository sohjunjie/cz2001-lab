// Vertex Class
public class Vertex {
	private final int id;        // id of Vertex is Integer Value from 0 to (Vertices - 1)
	private boolean marked;      // marked attribute tells whether vertex has been marked or not
	                             // This for marking vertices to implement BFS
	
	public Vertex(int num){     // Class constructor
		this.id = num;          
		this.marked = false;
	}

	public int getId() {
		return id;
	}
	
	public void mark(){
		this.marked = true;
	}
	
	public void unMark(){
		this.marked = false;
	}

	public boolean isMarked() {
		return marked;
	}

}
