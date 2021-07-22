import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// A class to store a graph edge

// A class to represent a graph object
class Graphh {
	// A list of lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graphh(List<Edge> edges, int N) {
		adjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}

public class DAG {
	// Perform DFS on the graph and set the departure time of all
	// vertices of the graph
	private static int DFS(Graphh graph, int v, boolean[] discovered, int[] departure, int time) {
		// mark the current node as discovered
		discovered[v] = true;

		// do for every edge `v —> u`
		for (int u : graph.adjList.get(v)) {
			// if `u` is not yet discovered
			if (!discovered[u]) {
				time = DFS(graph, u, discovered, departure, time);
			}
		}

		// ready to backtrack
		// set departure time of vertex `v`
		departure[v] = time++;

		return time;
	}

	// Returns true if given directed graph is DAG
	public static boolean isDAG(Graphh graph, int N) {
		// keep track of whether a vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// keep track of the departure time of a vertex in DFS
		int[] departure = new int[N];

		int time = 0;

		// Perform DFS traversal from all undiscovered vertices
		// to visit all connected components of a graph
		for (int i = 0; i < N; i++) {
			if (discovered[i] == false) {
				time = DFS(graph, i, discovered, departure, time);
			}
		}

		// check if the given directed graph is DAG or not
		for (int u = 0; u < N; u++) {
			for (int v : graph.adjList.get(u)) {
				if (departure[u] <= departure[v]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] origin = new int[12];
		int[] destination = new int[12];
		int[] weight = new int[12];
		try {
			File myObj = new File("input.txt");
			Scanner myReader = new Scanner(myObj);
			int[] input = new int[100];
			int i = 0;
			while (myReader.hasNextInt()) {
				input[i++] = myReader.nextInt();

			}
			for (int j = 0; j < input.length; j++) {
				// System.out.print(input[j] + " ");

			}

			int count1 = 0, count2 = 1, count3 = 2;
			for (int k = 0; k < origin.length; k++) {
				origin[k] = input[count1];
				count1 = count1 + 3;
				// System.out.print(origin[k]);
			}
			System.out.println();
			for (int k = 0; k < destination.length; k++) {
				destination[k] = input[count2];
				count2 = count2 + 3;
				// System.out.print(destination[k]);
			}
			System.out.println();
			for (int k = 0; k < weight.length; k++) {
				weight[k] = input[count3];
				count3 = count3 + 3;
				// System.out.print(weight[k]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		// List of graph edges as per the above diagram
		List<Edge> edges = Arrays.asList(new Edge(origin[0], destination[0], weight[0]),
				new Edge(origin[1], destination[1], weight[1]), new Edge(origin[2], destination[2], weight[2]),
				new Edge(origin[3], destination[3], weight[3]), new Edge(origin[4], destination[4], weight[4]),
				new Edge(origin[5], destination[5], weight[5]), new Edge(origin[6], destination[6], weight[6]),
				new Edge(origin[7], destination[7], weight[7]), new Edge(origin[8], destination[8], weight[8]),
				new Edge(origin[9], destination[9], weight[9]), new Edge(origin[10], destination[10], weight[10]),
				new Edge(origin[11], destination[11], weight[11]));

		final int N = 6;

		Graphh graph = new Graphh(edges, N);
		if (isDAG(graph, N)) {
			System.out.println("The graph is a DAG");
		} else {
			System.out.println("The graph is not a DAG");
		}
	}
}