import java.util.*;

public class BellmanFordCorrectOrder {
    static class Edge {
        String from, to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "," + to;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        List<String> vertices = Arrays.asList("S", "A", "B", "C", "D", "E", "F", "G", "H", "I");

        Edge[] edgeOrder = new Edge[]{
            new Edge("A", "B", 4), new Edge("A", "C", 2),
            new Edge("B", "G", 2), new Edge("B", "H", 4),
            new Edge("C", "D", 2), new Edge("C", "F", 1),
            new Edge("E", "F", 3), new Edge("E", "H", 3),
            new Edge("F", "D", 3), new Edge("G", "I", 1),
            new Edge("H", "G", 1), new Edge("I", "H", 1),
            new Edge("S", "A", 7), new Edge("S", "C", 6),
            new Edge("S", "E", 6), new Edge("S", "F", 5)
        };

        // Initialize distances
        Map<String, Integer> dist = new HashMap<>();
        for (String v : vertices) dist.put(v, INF);
        dist.put("S", 0); // source

        // Header
        System.out.print(String.format("%-8s", "Rlx order"));
        for (String v : vertices) {
            System.out.print(String.format("%-6s", v));
        }
        System.out.println();

        // Bellman-Ford loop
        for (int pass = 1; pass <= vertices.size() - 1; pass++) {
            for (Edge edge : edgeOrder) {
                int uDist = dist.get(edge.from);
                if (uDist != INF && uDist + edge.weight < dist.get(edge.to)) {
                    dist.put(edge.to, uDist + edge.weight);
                }

                // Print one row: edge label and current dist table
                System.out.print(String.format("%-8s", edge));
                for (String v : vertices) {
                    int d = dist.get(v);
                    String val = (d == INF) ? "∞" : Integer.toString(d);
                    System.out.print(String.format("%-6s", val));
                }
                System.out.println();
            }
            System.out.println("------------------------------------------------");
        }
    }
}
