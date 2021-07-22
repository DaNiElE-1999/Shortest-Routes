public class Graph {
	public static final int INF = Integer.MAX_VALUE;
	
	public int V;
	public int matrix[][];

	public Graph(int v) {
		this.V = v;
		matrix = new int[v][v];
		
		//Initialize to INF at first
		for (int i = 0; i < v; i++)
			for (int j = 0; j < v; j++)
				//matrix[i][j] = INF;
				matrix[i][j] = i!= j ? INF : 0;
	}

	public void addEdge(int source, int destination, double WEIGHT) {
		// add edge
		int weight = (int) WEIGHT;
		matrix[source][destination] = weight;
	}
	
	public void printGraph() {
		for (int j = 0; j < V; j++) {
			for (int i = 0; i < V; i++) {
				String s = matrix[j][i] != INF ? String.format("%05d", matrix[j][i]) : "  INF";
				System.out.print("" + s + ", ");
			}
			System.out.println();
		}
	}
	
	
	//Check if graph is DAG:
	private static int DFS(Graph graph, int v, boolean[] discovered, int[] departure, int time) {
		// mark the current node as discovered
		discovered[v] = true;

		// do for every edge `v —> u` for v!=u
		for (int u = 0; u < graph.V; u++) {
			if (graph.matrix[v][u] == Graph.INF)
				continue;
		
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
	public static boolean isDAG(Graph graph) {
		int N = graph.V;
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
			for (int v = 0; v < N; v++) {
				if (u==v || graph.matrix[u][v] == Graph.INF)
					continue;
				
				if (departure[u] <= departure[v]) {
					return false;
				}
			}
		}
		return true;
	}
}