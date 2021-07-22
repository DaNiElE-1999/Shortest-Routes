import java.util.Stack;

public class TopologicalSorting {
	// Fill the stack recursively, DFS.
	static void fillStack(int v, Boolean visited[], Stack<Integer> stack, int[][] adj)
    {
        // Visit node
        visited[v] = true;

        // Visit all vertices originated from v
        int[] from_v = adj[v];
        int num = from_v.length;
        for (int i = 0; i < num; i++)
        {
        	// From v to i
            int edge = from_v[i];
            
            // Only proceed if edge hasn't an INFINITY weight (v and i not connected)
            if (edge != Integer.MAX_VALUE && !visited[i])
            	fillStack(i, visited, stack, adj);
        }
        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }
	
	static void shortestPath(int s, Graph g)
    {
		int V = g.V;
		
        Stack<Integer> stack = new Stack<Integer>();
        int dist[] = new int[V];

        // No vertex is visited initially
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillStack(i, visited, stack, g.matrix);

        // Initialize the distances.
        for (int i = 0; i < V; i++)
            dist[i] = Graph.INF;
        dist[s] = 0;

        // Process vertices in topological order
        while (stack.empty() == false)
        {
            // Get the next vertex from topological order
            int u = (int)stack.pop();

            // Update distances of all adjacent vertices
            int[] from_u = g.matrix[u];
            int num = from_u.length;
            if (dist[u] != Graph.INF)
            {
                for(int i = 0; i < num; i++)
                {
                    if (from_u[i] == Graph.INF)
                    	continue;
                    
                    long distI = (long)dist[i];
                    long distU = (long)dist[u];
                    long weight = (long)from_u[i];
                    
                    if (distI > distU + weight)
                        dist[i] = dist[u] + from_u[i];
                }
            }
        }

        // Print the calculated shortest distances
        for (int i = 0; i < g.V; i++)
        {
            if (dist[i] == Graph.INF)
                System.out.print( "INF ");
            else
                System.out.print( dist[i] + " ");
        }
    }
	
}
