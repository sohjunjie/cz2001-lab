import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	private int V;                   // Number of vertices of graph
	private int E;                         // Number of edges of graph
	private AdjacencyList adjacencyList;   // adjacency list of graph
	private int[][] matrixDistance;       // matrixDistance to keep track of shortest path distance between vertices. 
	                                       //MatrixDistance[i][j] = shortest path distance between vertices i and j
	private int[][] matrixPredecessor;    // MatrixPredecessor keeps track of predecessor vertex along shortest path.
	                                      // MatrixPredecessor[i][j] = id of vertex which comes before vertex
	                                      // with id 'j' in the shortest path from vertex 'i' to 'j'
	
	public int getV() {  // Method to return number of vertices
		return V;
	}
	
	public int getE() {  //Method to return edges of graph
		return E;
	}
	
	public Graph(int vertices){ //Class constructor
		this.V = vertices;     //Initialize V
		this.E = 0;            // Initially graph has no edges
		this.adjacencyList = new AdjacencyList(vertices);  //Initialize adjacency list
		this.matrixDistance = new int[vertices][vertices];  //Initialize matrixDistance
		this.matrixPredecessor = new int[vertices][vertices];  //Initialize matrixPredecessor
		
	}
	
	public void addEdge(int startVertexNum,int endVertexNum){  //Method to add edge to graph given id of startVertex and endVertex
			this.E++;    //Increment number of edges
			adjacencyList.addEdge(startVertexNum, endVertexNum); // Add edge to adjacency list
	}
	
	public boolean edgeAlreadyPresent(int startVertexNum, int endVertexNum){
		return (adjacencyList.edgeAlreadyPresent(startVertexNum, endVertexNum)); //Invoke corresponding method of AdjacencyList class
	}
	
	public LinkedList<Vertex> getAdjacencyListOfVertex(Vertex vertex){ //Method to get adjacency list given vertex
		return adjacencyList.getAdjacencyListOfVertex(vertex); // Invoke corresponding method of AdjacencyList class
	}
	
	public void generateRandomGraph(int edges){  //Method to generate random graph given number of edges required
		while(this.getE() < edges){ // while number of edges of graph is less than required
			int startVertexNum = (int) (Math.random() * V);  //Randomly generate startVertex id
			int endVertexNum = (int) (Math.random() * V);    //Randomly generate endVertex id
			if( startVertexNum != endVertexNum && !edgeAlreadyPresent(startVertexNum,endVertexNum)) // No self loops and no parallel edges
				this.addEdge(startVertexNum, endVertexNum); // add edge to graph
		}
	}
	
	// ALGORITHM FOR BFS
	// 1. for each vertex u of graph G
	// 2.    u.unMark()     
	// 3.    set distance from startVertex to vertex u = -1
	// 4. mark startVertex
	// 5. set distance of startVertex from startVertex = 0
	// 6. push startVertex to empty queue
	// 7. while(queue is not empty)
	// 8.   vertex = queue.remove()
	// 9.   for all vertices iteratorVertex in adjacency list of vertex
	// 10.        if(iteratorVertex is unmarked)
	// 11.            mark iteratorVertex;
	// 12.            distance between iteratorVertex and startVertex = distance between vertex and startVertex + 1
	// 13.            predecessor of iteratorVertex in shortest path of startVertex = vertex
	// 14.            push iteratorVertex to queue
	
	private void runBFS(int startVertexNum){ //Method to run BFS given id of startVertex

		Vertex startVertex = adjacencyList.getVertex(startVertexNum);
		Vertex vertex;  // vertex for processing
		LinkedList<Vertex> adjacencyListOfVertex; // adjacencyListOfVertex to hold adjacency list of given vertex
		Queue<Vertex> queue = new LinkedList<>(); // Queue to hold vertex
		
		// unmark all graph vertices and set default distance from startVertex
		for(int i = 0; i < this.getV(); i++){
			vertex = adjacencyList.getVertex(i);
			vertex.unMark();
			matrixDistance[startVertex.getId()][vertex.getId()] = -1;
		}
		
		startVertex.mark(); //Mark startVertex
		matrixDistance[startVertex.getId()][startVertex.getId()] = 0; //set distance of startVertex from itself = 0
		queue.add(startVertex);  // push startVertex to queue
		
		while(!queue.isEmpty()){
			vertex = queue.remove(); // push vertex from front of queue
			adjacencyListOfVertex = this.getAdjacencyListOfVertex(vertex); //retrieve adjacencyList of this vertex
			
			for(Vertex neighborVertex : adjacencyListOfVertex){
				if(! neighborVertex.isMarked()){
					neighborVertex.mark();
					matrixDistance[startVertex.getId()][neighborVertex.getId()] = matrixDistance[startVertex.getId()][vertex.getId()] + 1;
					matrixPredecessor[startVertex.getId()][neighborVertex.getId()] = vertex.getId();
					queue.add(neighborVertex);
				}
			}
			
		}

	
	}
	
	public void allPairsShortestPath(){ //Method to calculate all pairs shortest path in graph
		for(int i = 0;i < this.V;i++)   //Run the BFS algorithm on all vertices of graph
			this.runBFS(i);
	}
}
