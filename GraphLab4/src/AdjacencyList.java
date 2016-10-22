import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class AdjacencyList implements Serializable{ // class constructor

	private static final long serialVersionUID = 7073346916300226118L;
	private final LinkedHashMap<Vertex,LinkedList<Vertex>>  adjacencyList;   // LinkedHashmap : The key is a vertex - value is LinkedList of adjacent vertices
	private final LinkedHashMap<Integer, Vertex> idToVertex;                 // LinkedHashMap : The key is integer from 0 to (Vertices - 1) - Value is Vertex with corresponding id
	
	public AdjacencyList(int vertices){
		adjacencyList = (LinkedHashMap<Vertex,LinkedList<Vertex>>) new LinkedHashMap(); // Initialize adjacencylist
		idToVertex = (LinkedHashMap<Integer,Vertex>) new LinkedHashMap();               // Initialize idToVertex
		
		for (int i = 0; i<vertices; ++i ){
			Vertex vertex = new Vertex(i);
			adjacencyList.put(vertex, new LinkedList<Vertex>());  // Put Key-Value pair i.e. (Vertex, LinkedList of adjacent vertices) in LinkedHashMap
			idToVertex.put(vertex.getId(), vertex); // Put Key - Value pair i.e. (int id, corresponding vertex) in LinkedHashMap
		}
	}
	
	public void addEdge(int startVertexNum, int endVertexNum){
		Vertex startVertex, endVertex;
		LinkedList <Vertex> linkedListOne, linkedListTwo;        // Declare two LinkedLists to hold adjacency lists of startVertex and endVertex
		
		startVertex = idToVertex.get(startVertexNum);           // Input startVertex id to get corresponding startVertex from LinkedhashMap
		endVertex = idToVertex.get(endVertexNum);               // Input endVertex id to get corresponding endVertex from LinkedhashMap
		
		linkedListOne = adjacencyList.get(startVertex);         // Get adjacency list of startVertex in LinkedListOne
		linkedListTwo = adjacencyList.get(endVertex);           // Get adjacency list of endVertex in LinkedListTwo
		
		//Since we have undirected graphs so we have to add endVertex to adjacency list of startVertex and vice-versa
		linkedListOne.add(endVertex);               // add endVertex to adjacency list of startVertex
		linkedListTwo.add(startVertex);             // add startVertex to adjacency list of endVertex
	}
	
	public boolean edgeAlreadyPresent(int startVertexNum, int endVertexNum){ // Method to check if edge is already present in graph or not 
		                                                             //given integer id of startVertex and endVertex
		Vertex startVertex, endVertex;                       // Declare startVertex and endVertex
		LinkedList <Vertex> linkedListOne,linkedListTwo;     // Declare two LinkedLists to hold adjacency lists of startVertex and endVertex
		
		startVertex = idToVertex.get(startVertexNum);       // Input startVertex id to get corresponding startVertex from LinkedhashMap
		endVertex = idToVertex.get(endVertexNum);           // Input endVertex id to get corresponding endVertex from LinkedhashMap
		
		linkedListOne = adjacencyList.get(startVertex);     // Get adjacency list of startVertex in LinkedListOne
		linkedListTwo = adjacencyList.get(endVertex);       // Get adjacency list of endVertex in LinkedListTwo
		
		if(linkedListOne.contains(endVertex) || linkedListTwo.contains(startVertex)) // if startVertex is in adjacency list of endVertex or
			                                                                      //vice-versa than edge is already present in graph
			return true;
		else
			return false;
	}
	
    public LinkedList<Vertex> getAdjacencyListOfVertex(Vertex vertex){ //Method to return adjacency list of vertex given vertex id
		return adjacencyList.get(vertex);    // 
	}
    
    public Vertex getVertex(int vertexNum){ //Method to return vertex given vertex id
    	return idToVertex.get(vertexNum);
    }
}
