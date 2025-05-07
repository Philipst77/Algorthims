import java.util.*;

public class Instance2Kruskals {
    static class Edge {
        String u, v;
        int weight;

        Edge(String u, String v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class UnionFind {
        Map<String, String> parent = new HashMap<>();

        public UnionFind(Set<String> vertices) {
            for (String v : vertices)
                parent.put(v, v);
        }

        String find(String x) {
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        boolean union(String x, String y) {
            String rootX = find(x), rootY = find(y);
            if (rootX.equals(rootY)) return false;
            parent.put(rootX, rootY);
            return true;
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>(List.of(
            new Edge("B", "C", 1),
            new Edge("C", "D", 2),
            new Edge("B", "D", 2),
            new Edge("A", "D", 4),
            new Edge("A", "B", 5),
            new Edge("D", "F", 4),
            new Edge("C", "F", 3),
            new Edge("C", "E", 5),
            new Edge("F", "E", 4)
        ));

        Set<String> vertices = new HashSet<>(List.of("A", "B", "C", "D", "E", "F"));
        edges.sort(Comparator.comparingInt((Edge e) -> e.weight)
                .thenComparing(e -> e.u)
                .thenComparing(e -> e.v));

        UnionFind uf = new UnionFind(vertices);
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                mst.add(e);
                totalCost += e.weight;
            }
        }

        System.out.println("Selected edges for MST:");
        for (Edge e : mst)
            System.out.printf("(%s, %s) - %d\n", e.u, e.v, e.weight);

        System.out.println("Total cost of MST: " + totalCost);
    }
}
