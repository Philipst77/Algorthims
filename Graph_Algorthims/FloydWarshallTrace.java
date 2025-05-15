import java.util.*;

public class FloydWarshallTrace {
    static final int INF = 9999;

    public static void main(String[] args) {
        List<String> nodes = List.of("A", "B", "C", "D");
        int n = nodes.size();

        // Initialize adjacency matrix with INF and 0 on diagonals
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 0; i < n; i++)
            dist[i][i] = 0;

        // Map node names to indices
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) index.put(nodes.get(i), i);

        // Fill in graph edges based on your diagram
        dist[index.get("A")][index.get("C")] = 1;
        dist[index.get("A")][index.get("B")] = 4;
        dist[index.get("B")][index.get("D")] = 5;
        dist[index.get("C")][index.get("B")] = 2;
        dist[index.get("C")][index.get("D")] = 6;

        // Floyd–Warshall algorithm
        for (int k = 0; k < n; k++) {
            System.out.println("D(" + k + "):");
            printMatrix(dist, nodes);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // Final matrix
        System.out.println("D(" + n + "):");
        printMatrix(dist, nodes);
    }

    static void printMatrix(int[][] matrix, List<String> nodes) {
        System.out.print("\t");
        for (String col : nodes) System.out.print(col + "\t");
        System.out.println();
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i) + "\t");
            for (int j = 0; j < nodes.size(); j++) {
                if (matrix[i][j] == INF)
                    System.out.print("∞\t");
                else
                    System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
