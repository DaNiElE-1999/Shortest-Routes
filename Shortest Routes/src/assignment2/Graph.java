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
				matrix[i][j] = INF;
	}

	public void addEdge(int source, int destination, double WEIGHT) {
		// add edge
		int weight = (int) WEIGHT;
		matrix[source][destination] = weight;
	}
	
	public void printGraph() {
		for (int j = 0; i < V; i++) {
			for (int i = 0; j < V; j++) {
				System.out.print("" + matrix[j][i] + ", ");
			}
			System.out.println();
		}
	}
}