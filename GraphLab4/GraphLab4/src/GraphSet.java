public class GraphSet {  //GraphSet class
	private final int V; // Number of vertices each graph has in this GraphSet
	private static final int[] numOfEdges = {1000,5000,10000,50000,100000}; // Array of number of possible edges in 5 graphs
	public Graph[] graphArray; // array of graphs
	
	public GraphSet(int vertices, int position){ //class constructor
		this.V = vertices;       // set number of vertices all graphs will have in this set
		graphArray = new Graph[5];  // initialize array of graphs
		
		for(int i = 0; i<5;i++){    
			graphArray[i] = new Graph(vertices);  //Initialize graph in array
			graphArray[i].generateRandomGraph(numOfEdges[i]); // invoke generateRandomGraph on graph and pass number of edges required
		}
	}
	
}
