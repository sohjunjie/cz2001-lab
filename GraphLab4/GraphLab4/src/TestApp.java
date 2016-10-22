
public class TestApp {
	public static void main(String[] args){
		Graph g = new Graph(10000);
		//g.generateRandomGraph(100000);
		//g.allPairsShortestPath();
		//g.printAllPairsShortestPath();
		for(int i=0;i<10000;i++)
			for(int j=0;j<10000;j++)
				System.out.println(i);
	}
}
