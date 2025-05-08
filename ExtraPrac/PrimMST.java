import java.util.*;

public class PrimMST {
    static class Edge {
        char from, to;
        int weight;

        Edge(char from, char to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Map<Character, List<Edge>> graph = new HashMap<>();

        // Initialize adjacency list
        for (char c : "ABCDEF".toCharArray()) {
            graph.put(c, new ArrayList<>());
        }

        // Add edges (undirected)
        addEdge(graph, 'A', 'B', 1);
        addEdge(graph, 'C', 'D', 1);
        addEdge(graph, 'D', 'E', 1);
        addEdge(graph, 'A', 'C', 2);
        addEdge(graph, 'B', 'C', 2);
        addEdge(graph, 'D', 'A', 4);
        addEdge(graph, 'E', 'F', 8);
        addEdge(graph, 'A', 'F', 10);

        Set<Character> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator
                .comparingInt((Edge e) -> e.weight)
                .thenComparing(e -> e.to)
                .thenComparing(e -> e.from)
        );

        char start = 'D';
        visited.add(start);
        pq.addAll(graph.get(start));

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        while (!pq.isEmpty() && mst.size() < graph.size() - 1) {
            Edge edge = pq.poll();
            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            mst.add(edge);
            totalCost += edge.weight;

            for (Edge next : graph.get(edge.to)) {
                if (!visited.contains(next.to)) {
                    pq.offer(next);
                }
            }
        }

        System.out.println("Edges in MST (Prim's, from root D):");
        for (Edge e : mst) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }
        System.out.println("Total Cost: " + totalCost);
    }

    static void addEdge(Map<Character, List<Edge>> graph, char u, char v, int weight) {
        graph.get(u).add(new Edge(u, v, weight));
        graph.get(v).add(new Edge(v, u, weight));
    }
}
