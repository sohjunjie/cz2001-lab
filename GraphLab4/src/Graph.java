import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Graph implements Serializable{

	private static final long serialVersionUID = -8413673332288116294L;
	private int numVertices;
	private int numEdges;
	private AdjacencyList adjacencyList;   // adjacency list of graph
	private int[][] matrixDistance;       // matrixDistance to keep track of shortest path distance between vertices. 
	                                       //MatrixDistance[i][j] = shortest path distance between vertices i and j
	private int[][] matrixPredecessor;    // MatrixPredecessor keeps track of predecessor vertex along shortest path.
	                                      // MatrixPredecessor[i][j] = id of vertex which comes before vertex
	                                      // with id 'j' in the shortest path from vertex 'i' to 'j'
	
	public int getnumVertices(){ return numVertices; }
	public int getNumEdges(){ return numEdges; }
	public int[][] getMatrixDistance(){ return this.matrixDistance; }
	
	public Graph(int numVertices, int numEdges){
		this.numVertices = numVertices;
		this.numEdges = 0;
		this.adjacencyList = new AdjacencyList(numVertices);  //Initialize adjacency list
		this.matrixDistance = new int[numVertices][numVertices];  //Initialize matrixDistance
		this.matrixPredecessor = new int[numVertices][numVertices];  //Initialize matrixPredecessor
		this.generateRandomGraph(numEdges);
	}
	
	public void addEdge(int startVertexNum, int endVertexNum){
			this.adjacencyList.addEdge(startVertexNum, endVertexNum); // Add edge to adjacency list
			this.numEdges++;
	}
	
	public boolean edgeAlreadyPresent(int startVertexNum, int endVertexNum){
		return (adjacencyList.edgeAlreadyPresent(startVertexNum, endVertexNum)); //Invoke corresponding method of AdjacencyList class
	}
	
	public LinkedList<Vertex> getVertexAdjacencyList(Vertex vertex){
		return adjacencyList.getAdjacencyListOfVertex(vertex); // Invoke corresponding method of AdjacencyList class
	}
	
	public void generateRandomGraph(int numEdgeRequired){
		
		while(this.getNumEdges() < numEdgeRequired){
			int startVertexNum = (int) (Math.random() * numVertices);  //Randomly generate startVertex id
			int endVertexNum = (int) (Math.random() * numVertices);    //Randomly generate endVertex id
			if( startVertexNum != endVertexNum && !edgeAlreadyPresent(startVertexNum,endVertexNum)) // No self loops and no parallel edges
				this.addEdge(startVertexNum, endVertexNum); // add edge to graph
		}

	}
	
	private void runBFS(int startVertexNum){ //Method to run BFS given id of startVertex

		Vertex startVertex = adjacencyList.getVertex(startVertexNum);
		Vertex vertex;
		LinkedList<Vertex> vertexAdjacencyList;
		Queue<Vertex> queue = new LinkedList<>();
		
		// Unmark all graph vertices and set default distance from startVertex
		for(int i = 0; i < this.getnumVertices(); i++){
			vertex = adjacencyList.getVertex(i);
			vertex.unMark();
			matrixDistance[startVertex.getId()][vertex.getId()] = -1;
		}
		
		startVertex.mark();
		matrixDistance[startVertex.getId()][startVertex.getId()] = 0;
		queue.add(startVertex);

		while(!queue.isEmpty()){
			vertex = queue.remove(); // push vertex from front of queue
			vertexAdjacencyList = this.getVertexAdjacencyList(vertex); //retrieve adjacencyList of this vertex
			
			for(Vertex neighborVertex : vertexAdjacencyList){
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
		for(int i = 0;i < this.numVertices;i++)   //Run the BFS algorithm on all vertices of graph
			this.runBFS(i);
	}
}
