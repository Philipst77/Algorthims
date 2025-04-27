import java.util.*;

public class Instance2Prims {
    static class Edge {
        String u, v;
        int weight;

        Edge(String u, String v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for (String v : List.of("A", "B", "C", "D", "E", "F"))
            graph.put(v, new ArrayList<>());

        // Add undirected edges (same as Kruskal’s input)
        addEdge(graph, "B", "C", 1);
        addEdge(graph, "C", "D", 2);
        addEdge(graph, "B", "D", 2);
        addEdge(graph, "A", "D", 4);
        addEdge(graph, "A", "B", 5);
        addEdge(graph, "D", "F", 4);
        addEdge(graph, "C", "F", 3);
        addEdge(graph, "C", "E", 5);
        addEdge(graph, "F", "E", 4);

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(
            Comparator.comparingInt((Edge e) -> e.weight)
                      .thenComparing(e -> e.u)
                      .thenComparing(e -> e.v)
        );

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        String start = "A";
        visited.add(start);
        pq.addAll(graph.get(start));

        while (mst.size() < graph.size() - 1 && !pq.isEmpty()) {
            Edge e = pq.poll();
            String next = visited.contains(e.u) ? e.v : e.u;

            if (visited.contains(e.u) && visited.contains(e.v)) continue;

            visited.add(next);
            mst.add(e);
            totalCost += e.weight;

            for (Edge neighbor : graph.get(next)) {
                if (!visited.contains(neighbor.u) || !visited.contains(neighbor.v)) {
                    pq.add(neighbor);
                }
            }
        }

        System.out.println("Selected edges for MST (Prim’s):");
        for (Edge e : mst)
            System.out.printf("(%s, %s) - %d\n", e.u, e.v, e.weight);

        System.out.println("Total cost of MST: " + totalCost);
    }

    static void addEdge(Map<String, List<Edge>> graph, String u, String v, int weight) {
        graph.get(u).add(new Edge(u, v, weight));
        graph.get(v).add(new Edge(v, u, weight));
    }
}
