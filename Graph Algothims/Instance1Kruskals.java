import java.util.*;

public class Instance1Kruskals {
    static class Edge {
        String u, v;
        int weight;

        Edge(String u, String v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class DisjointSet {
        private final Map<String, String> parent = new HashMap<>();

        void makeSet(Set<String> vertices) {
            for (String v : vertices)
                parent.put(v, v);
        }

        String find(String x) {
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        void union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            if (!rootX.equals(rootY))
                parent.put(rootY, rootX);
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>(List.of(
            new Edge("A", "C", 1),
            new Edge("A", "B", 4),
            new Edge("A", "D", 3),
            new Edge("B", "C", 4),
            new Edge("B", "D", 4),
            new Edge("C", "D", 2),
            new Edge("C", "F", 4),
            new Edge("D", "F", 6),
            new Edge("E", "F", 5)
        ));

        // Sort by weight, then u, then v (alphabetically)
        edges.sort(Comparator.comparingInt((Edge e) -> e.weight)
                .thenComparing(e -> e.u)
                .thenComparing(e -> e.v));

        Set<String> vertices = new HashSet<>(List.of("A", "B", "C", "D", "E", "F"));
        DisjointSet ds = new DisjointSet();
        ds.makeSet(vertices);

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for (Edge e : edges) {
            if (!ds.find(e.u).equals(ds.find(e.v))) {
                ds.union(e.u, e.v);
                mst.add(e);
                totalCost += e.weight;
            }
        }

        System.out.println("Selected edges and their weights:");
        for (Edge e : mst) {
            System.out.printf("(%s, %s) - %d\n", e.u, e.v, e.weight);
        }

        System.out.println("Total cost of MST: " + totalCost);
    }
}
