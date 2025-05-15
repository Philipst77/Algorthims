import java.util.*;

public class FloydWarshall {
    static final int INF = 9999;

    public static void main(String[] args) {
        List<String> nodes = List.of("1", "2", "3", "4"); // node labels
        int n = nodes.size();

        // Initialize adjacency matrix
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, INF);
        for (int i = 0; i < n; i++) dist[i][i] = 0;

        // Map node name to index
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) index.put(nodes.get(i), i);

        // Add edges from graph
        dist[index.get("1")][index.get("2")] = 8;
        dist[index.get("1")][index.get("4")] = 1;
        dist[index.get("2")][index.get("3")] = 1;
        dist[index.get("3")][index.get("1")] = 4;
        dist[index.get("4")][index.get("2")] = 2;
        dist[index.get("4")][index.get("3")] = 9;

        // Print D(0)
        System.out.println("D(0):");
        printMatrix(dist, nodes);

        // Floyd–Warshall updates
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
            System.out.println("D(" + (k + 1) + "):");
            printMatrix(dist, nodes);
        }
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
