import java.util.*;

class Edge {
    String from, to;
    int capacity;
    int flow;

    public Edge(String from, String to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    @Override
    public String toString() {
        return from + " → " + to + " | flow/capacity: " + flow + "/" + capacity;
    }
}

class Graph {
    Map<String, List<Edge>> adj;
    Map<String, Map<String, Edge>> edgeLookup;

    public Graph() {
        adj = new HashMap<>();
        edgeLookup = new HashMap<>();
    }

    public void addEdge(String from, String to, int capacity) {
        adj.putIfAbsent(from, new ArrayList<>());
        Edge e = new Edge(from, to, capacity);
        adj.get(from).add(e);

        edgeLookup.putIfAbsent(from, new HashMap<>());
        edgeLookup.get(from).put(to, e);
    }

    public void applyAugmentingPath(List<String> path, int bottleneck) {
        for (int i = 0; i < path.size() - 1; i++) {
            String u = path.get(i);
            String v = path.get(i + 1);

            Edge forwardEdge = getEdge(u, v);
            if (forwardEdge != null) {
                forwardEdge.flow += bottleneck;
            } else {
                // It's a reverse edge — subtract flow
                Edge reverseEdge = getEdge(v, u);
                if (reverseEdge != null) {
                    reverseEdge.flow -= bottleneck;
                }
            }
        }
    }

    private Edge getEdge(String from, String to) {
        if (edgeLookup.containsKey(from)) {
            return edgeLookup.get(from).get(to);
        }
        return null;
    }

    public void printGraphWithTotalCapacity() {
        System.out.println("Updated flow / total capacity (fwdCap + revCap):\n");

        for (String from : adj.keySet()) {
            for (Edge edge : adj.get(from)) {
                int forwardFlow = edge.flow;
                int forwardCap = edge.capacity;

                int reverseCap = 0;
                Edge reverseEdge = getEdge(edge.to, edge.from);
                if (reverseEdge != null) {
                    reverseCap = reverseEdge.capacity;
                }

                int totalCap = forwardCap + reverseCap;

                System.out.println(edge.from + " → " + edge.to +
                        " = " + forwardFlow + "/" + totalCap);
            }
        }
    }
}

public class AugmentedPath2 {
    public static void main(String[] args) {
        Graph g = new Graph();

        // Forward edges
        g.addEdge("S", "A", 10);
        g.addEdge("S", "B", 3);
        g.addEdge("B", "A", 8);
        g.addEdge("B", "D", 6);
        g.addEdge("A", "C", 8);
        g.addEdge("A", "D", 4);
        g.addEdge("C", "D", 3);
        g.addEdge("D", "T", 8);

        // Reverse edges
        g.addEdge("B", "S", 2);
        g.addEdge("A", "B", 2);
        g.addEdge("T", "C", 2);
        g.addEdge("C", "A", 2);

        // Apply augmenting path: S → A → B → D → T
        List<String> path = Arrays.asList("S", "A", "B", "D", "T");
        int bottleneck = 2;
        g.applyAugmentingPath(path, bottleneck);

        // Print updated flow and total capacity
        g.printGraphWithTotalCapacity();
    }
}
