import java.util.*;

public class KruskalMST {
    static class Edge {
        char u, v;
        int weight;

        Edge(char u, char v, int weight) {
            if (u > v) {  // ensure u has lower ASCII if tie
                char temp = u;
                u = v;
                v = temp;
            }
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class UnionFind {
        Map<Character, Character> parent = new HashMap<>();

        public void makeSet(char v) {
            parent.put(v, v);
        }

        public char find(char v) {
            if (parent.get(v) != v) {
                parent.put(v, find(parent.get(v)));
            }
            return parent.get(v);
        }

        public void union(char u, char v) {
            char rootU = find(u);
            char rootV = find(v);
            if (rootU != rootV) {
                parent.put(rootV, rootU);
            }
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('A', 'B', 1));
        edges.add(new Edge('C', 'D', 1));
        edges.add(new Edge('D', 'E', 1));
        edges.add(new Edge('A', 'C', 2));
        edges.add(new Edge('B', 'C', 2));
        edges.add(new Edge('D', 'A', 4));
        edges.add(new Edge('E', 'F', 8));
        edges.add(new Edge('A', 'F', 10));

        // Sort by weight, then by ASCII u, then v
        edges.sort(Comparator.comparingInt((Edge e) -> e.weight)
                .thenComparing(e -> e.u)
                .thenComparing(e -> e.v));

        UnionFind uf = new UnionFind();
        for (char v : "ABCDEF".toCharArray()) {
            uf.makeSet(v);
        }

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for (Edge edge : edges) {
            if (uf.find(edge.u) != uf.find(edge.v)) {
                mst.add(edge);
                uf.union(edge.u, edge.v);
                totalCost += edge.weight;
            }
        }

        System.out.println("Edges in MST (in order chosen):");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " : " + e.weight);
        }
        System.out.println("Total Cost: " + totalCost);
    }
}
