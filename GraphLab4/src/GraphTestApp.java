import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class GraphTestApp {
	
	public static final Path 	DATAPATH 		= Paths.get(System.getProperty("user.dir"), "data");
	public static final Scanner sc = new Scanner(System.in);
	
	private static final int[] 	NUM_EDGES		= {1000,5000,10000,50000,100000};
	private static final int[] 	NUM_VERTICES 	= {5000, 10000};
	
	public static void main(String[] args) {
		
		int choice;
		String statsStr;
				
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Generate and save graph");
            System.out.println("(2) Print graph statistics");
            System.out.println("(3) Query graph");
            System.out.println("(4) Exit");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: // generate and save graph for permutation of NUM_VERTICES and NUM_EDGES
	            		for(int numVertex : NUM_VERTICES)
	            			for(int numEdge : NUM_EDGES)
	            				saveGraphResults(numVertex, numEdge);
                        break;
                case 2:
                		statsStr = "";
	            		for(int numVertex : NUM_VERTICES)
	            			for(int numEdge : NUM_EDGES){
	            				statsStr += printPreprocessingStats(numVertex, numEdge) + "\n";
	            			}
						try {
							writeStatsToTxt(statsStr);
						} catch (IOException e) {
								e.printStackTrace();
						}
                        break;
                case 3:
                		showQueryGraphMenu();
                    	break;
                case 4:
            }

        } while (choice < 4);
        

	}
	
	public static void writeStatsToTxt(String textToWrite) throws IOException{
		
		Path saveStatsFile = Paths.get(DATAPATH.toString(), "stats.txt");
		
		File file = saveStatsFile.toFile();
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(textToWrite);
		bw.close();
		
	}
	
	public static void showQueryGraphMenu(){
		
		int choice;
		int numVertex = 0, numEdge = 0;
		boolean inputValid;
		
		do{
			System.out.println("Select number of vertices from following options");
            System.out.println("(1) 5000");
            System.out.println("(2) 10000");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
			
            inputValid = true;
            switch (choice) {
	            case 1: // generate and save graph for permutation of NUM_VERTICES and NUM_EDGES
	            		numVertex = 5000;
	                    break;
	            case 2:
            			numVertex = 10000;
            			break;
	            default:
	            		inputValid = false;
	            		break;
            }
            
		}while(!inputValid);

		
		do{
			System.out.println("Select number of edges from following options");
            System.out.println("(1) 1000");
            System.out.println("(2) 5000");
            System.out.println("(3) 10000");
            System.out.println("(4) 50000");
            System.out.println("(5) 100000");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
			
            inputValid = true;
            switch (choice) {
	            case 1: // generate and save graph for permutation of NUM_VERTICES and NUM_EDGES
	            		numEdge = 1000; break;
	            case 2:
	            		numEdge = 5000; break;
	            case 3:
	            		numEdge = 10000; break;
	            case 4:
	            		numEdge = 50000; break;
	            case 5:
	            		numEdge = 100000; break;
	            default:
	            		inputValid = false; break;
            }

		}while(!inputValid);
		
		int[][] queryGraphDist = loadGraphMatrixDist("V" + numVertex + "E" + numEdge + ".dat");
		// new code
		int[][] queryGraphPredecessor = loadGraphMatrixPredecessor("V" + numVertex + "E" + numEdge + "Predecessor" + ".dat");
		if(queryGraphDist == null){
			System.out.println("Preprocessed results for graph vertex = " + numVertex + "    edge = " + numEdge + " is not available.");
			return;
		}
		
		int inputVertex1 = 0;
		int inputVertex2 = 0;
		int dist;
		String shortestPathString;
		do{
			
			System.out.print("Enter 1st vertex number to query (0~" + (numVertex-1) + ") :"); inputVertex1 = sc.nextInt();
			if(inputVertex1 < 0) return;
			
			System.out.print("Enter 2nd vertex number to query (0~" + (numVertex-1) + ") :"); inputVertex2 = sc.nextInt();			
			if(inputVertex2 < 0) return;
			
			System.out.println("To quit enter -1.");
			
			dist = queryGraphDist[inputVertex1][inputVertex2];
			
			System.out.println("Distance between VertexNum" + inputVertex1 + " and VertexNum" + inputVertex2 + " is " + dist + ".");
			
			//new code
			if(dist != -1){
				shortestPathString = Graph.toStringBFSPath(inputVertex1, inputVertex2, queryGraphPredecessor);
				System.out.println(shortestPathString);
			}

		}while(true);
		
		
	}
	
	public static void saveGraphResults(int numVertex, int numEdge){
		Graph graph = null;
		graph = new Graph(numVertex, numEdge);
		graph.allPairsShortestPath();
		saveGraphMatrixDist(graph);
		saveGraphMatrixPredecessor(graph);
	}
	
	public static String printPreprocessingStats(int numVertex, int numEdge){
		
		Graph graph = new Graph(numVertex, numEdge);
		long start, end, totalTime = 0;
		
		start = System.nanoTime();
		graph.allPairsShortestPath();
		end = System.nanoTime();
		totalTime += (end-start);
		
		String printStr = "Vertices = " + graph.getnumVertices() + ",    Edge = " + graph.getNumEdges() + "    nanoTimeTaken = " + totalTime;
		System.out.println(printStr);
		
		return printStr;
		
	}
	
	public static int[][] loadGraphMatrixDist(String loadFileName){
		
		Path saveData 			= Paths.get(DATAPATH.toString(), loadFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		int[][] matrixDist		= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			matrixDist = (int[][]) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(loadFileName + " not found or does not exists.");
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + loadFileName + " is corrupted.");
		}
		
		return matrixDist;
		
	}
	
	public static int[][] loadGraphMatrixPredecessor(String loadFileName){
		
		Path saveData 			= Paths.get(DATAPATH.toString(), loadFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		int[][] matrixPredecessor		= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			matrixPredecessor = (int[][]) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(loadFileName + " not found or does not exists.");
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + loadFileName + " is corrupted.");
		}
		
		return matrixPredecessor;	
	}
	
	public static void saveGraphMatrixDist(Graph graph){
		
		String	saveFileName = "V" + graph.getnumVertices() + "E" + graph.getNumEdges() + ".dat";
		Path	saveFilePath = Paths.get(DATAPATH.toString(), saveFileName);		
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFilePath.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(graph.getMatrixDistance());
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void saveGraphMatrixPredecessor(Graph graph){
		
		String saveFileName = "V" + graph.getnumVertices() + "E" + graph.getNumEdges() + "Predecessor" + ".dat";
		Path saveFilePath = Paths.get(DATAPATH.toString(), saveFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFilePath.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(graph.getMatrixPredecessor());
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	

	
}
