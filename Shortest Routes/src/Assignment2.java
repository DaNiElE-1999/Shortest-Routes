import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) throws FileNotFoundException {
		// We build the graph from user input
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Please enter the filename: ");
		String filename = stdin.next();
		
		File f = new File(filename);
		
		Scanner sc = new Scanner(f);
		
		int N = sc.nextInt(), E = sc.nextInt();
		
		// Create the graph
		Graph g = new Graph(N);
		
		for (int i = 0; i < E; i++) {
			int origin = sc.nextInt();
			int dest = sc.nextInt();
			double weight = sc.nextDouble();
			
			g.addEdge(origin, dest, weight);
		}
		
		g.printGraph();
		
		boolean isDag = Graph.isDAG(g);
		if (isDag) {
			// Graph is DAG
			System.out.println("Graph is DAG");
		} else {
			// Graph is not DAG
			System.out.println("Graph is not DAG");
		}
		
		System.out.println("Enter source vertex: ");
		int v = stdin.nextInt();
		
		if (isDag) {
			//We use topological sorting;
			TopologicalSorting.shortestPath(v, g);
		} else {
			// We use Djikstra's Algorithm
			Djikstra.getMinDistances(v, g);
		}
	}

}
