import java.util.*;

public class Instance1Prims {
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

        // Add undirected edges from original instance
        addEdge(graph, "A", "C", 1);
        addEdge(graph, "A", "B", 4);
        addEdge(graph, "A", "D", 3);
        addEdge(graph, "B", "C", 4);
        addEdge(graph, "B", "D", 4);
        addEdge(graph, "C", "D", 2);
        addEdge(graph, "C", "F", 4);
        addEdge(graph, "D", "F", 6);
        addEdge(graph, "E", "F", 5);

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
            String next = !visited.contains(e.u) ? e.u : e.v;

            if (visited.contains(e.u) && visited.contains(e.v)) continue;

            mst.add(e);
            totalCost += e.weight;
            visited.add(next);

            for (Edge edge : graph.get(next)) {
                if (!visited.contains(edge.u) || !visited.contains(edge.v)) {
                    pq.add(edge);
                }
            }
        }

        System.out.println("Selected edges and their weights (Prim's):");
        for (Edge e : mst) {
            System.out.printf("(%s, %s) - %d\n", e.u, e.v, e.weight);
        }

        System.out.println("Total cost of MST: " + totalCost);
    }

    static void addEdge(Map<String, List<Edge>> graph, String u, String v, int weight) {
        graph.get(u).add(new Edge(u, v, weight));
        graph.get(v).add(new Edge(v, u, weight));
    }
}
