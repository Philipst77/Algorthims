import java.util.*;

public class AugmentedPath1 {
    static final int V = 6; // Number of vertices: S, A, B, C, D, T

    static Map<String, Integer> nodeIndex = Map.of(
        "S", 0, "A", 1, "B", 2, "C", 3, "D", 4, "T", 5
    );

    public static void main(String[] args) {
        int[][] capacity = new int[V][V];
    
        // Forward edges (original and added ones)
        capacity[nodeIndex.get("S")][nodeIndex.get("A")] = 8;
        capacity[nodeIndex.get("S")][nodeIndex.get("B")] = 5;
        capacity[nodeIndex.get("A")][nodeIndex.get("C")] = 6;
        capacity[nodeIndex.get("B")][nodeIndex.get("A")] = 1;
        capacity[nodeIndex.get("B")][nodeIndex.get("C")] = 5;
        capacity[nodeIndex.get("B")][nodeIndex.get("D")] = 6;
        capacity[nodeIndex.get("C")][nodeIndex.get("T")] = 2;
        capacity[nodeIndex.get("D")][nodeIndex.get("C")] = 3;
        capacity[nodeIndex.get("D")][nodeIndex.get("T")] = 1;
    
        // Additional specified edges
        capacity[nodeIndex.get("A")][nodeIndex.get("S")] = 7;
        capacity[nodeIndex.get("C")][nodeIndex.get("A")] = 3;
        capacity[nodeIndex.get("T")][nodeIndex.get("D")] = 7;
        capacity[nodeIndex.get("D")][nodeIndex.get("A")] = 4; // ✅ NEW EDGE D → A
    
        printGraph(capacity);
        printAllAugmentingPaths(capacity, "S", "T");
    
        int maxFlow = fordFulkerson(capacity, nodeIndex.get("S"), nodeIndex.get("T"));
        System.out.println("\nThe maximum possible flow is: " + maxFlow);
    }
    

    public static int fordFulkerson(int[][] capacity, int source, int sink) {
        int[][] residual = new int[V][V];
        for (int u = 0; u < V; u++)
            for (int v = 0; v < V; v++)
                residual[u][v] = capacity[u][v];

        int[] parent = new int[V];
        int maxFlow = 0;

        while (dfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static boolean dfs(int[][] residual, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;
        parent[source] = -1;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    if (v == sink) return true;
                    stack.push(v);
                }
            }
        }
        return false;
    }

    public static void printGraph(int[][] capacity) {
        String[] labels = {"S", "A", "B", "C", "D", "T"};
        System.out.println("Graph Adjacency Matrix (Capacities):");
        System.out.print("     ");
        for (String label : labels) {
            System.out.printf("%5s", label);
        }
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.printf("%5s", labels[i]);
            for (int j = 0; j < V; j++) {
                System.out.printf("%5d", capacity[i][j]);
            }
            System.out.println();
        }
    }

    public static void printAllAugmentingPaths(int[][] capacity, String sourceLabel, String sinkLabel) {
        int source = nodeIndex.get(sourceLabel);
        int sink = nodeIndex.get(sinkLabel);
        boolean[] visited = new boolean[V];
        List<String> path = new ArrayList<>();
        System.out.println("\nAugmenting paths from " + sourceLabel + " to " + sinkLabel + ":");
        dfsPrintPaths(capacity, source, sink, visited, path);
    }

    private static void dfsPrintPaths(int[][] capacity, int current, int sink, boolean[] visited, List<String> path) {
        visited[current] = true;
        path.add(getNodeName(current));

        if (current == sink) {
            System.out.println(String.join(" -> ", path));
        } else {
            for (int v = 0; v < V; v++) {
                if (!visited[v] && capacity[current][v] > 0) {
                    dfsPrintPaths(capacity, v, sink, visited, path);
                }
            }
        }

        path.remove(path.size() - 1);
        visited[current] = false;
    }

    private static String getNodeName(int index) {
        for (Map.Entry<String, Integer> entry : nodeIndex.entrySet()) {
            if (entry.getValue() == index) return entry.getKey();
        }
        return "?";
    }
}
