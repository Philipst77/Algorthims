import java.util.*;

class Edge {
    String from, to;
    int capacity;
    int flow;

    public Edge(String from, String to, int capacity) {  // Constructor matches class name now
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int residualCapacity() {
        return capacity - flow;
    }

    @Override
    public String toString() {
        return from + " → " + to + " | flow/capacity: " + flow + "/" + capacity;
    }
}

class Graph {
    Map<String, List<Edge>> adj = new HashMap<>();
    Map<String, Map<String, Edge>> edgeLookup = new HashMap<>();

    public void addEdge(String from, String to, int capacity) {
        Edge e = new Edge(from, to, capacity);   // Use Edge, not Edge2
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(e);
        edgeLookup.computeIfAbsent(from, k -> new HashMap<>()).put(to, e);

        Edge rev = new Edge(to, from, 0);         // Reverse edge with 0 capacity
        adj.computeIfAbsent(to, k -> new ArrayList<>()).add(rev);
        edgeLookup.computeIfAbsent(to, k -> new HashMap<>()).put(from, rev);
    }

    public int fordFulkerson(String source, String sink) {
        int maxFlow = 0;
        int iteration = 1;
        while (true) {
            Map<String, String> parent = new HashMap<>();
            int bottleneck = bfs(source, sink, parent);
            if (bottleneck == 0) break;

            List<String> path = new ArrayList<>();
            String v = sink;
            while (!v.equals(source)) {
                path.add(v);
                v = parent.get(v);
            }
            path.add(source);
            Collections.reverse(path);

            System.out.println("--- Iteration " + iteration + " ---");
            System.out.println("Augmenting Path: " + String.join(" → ", path));
            System.out.println("Bottleneck = " + bottleneck);

            maxFlow += bottleneck;
            v = sink;
            while (!v.equals(source)) {
                String u = parent.get(v);
                Edge forward = edgeLookup.get(u).get(v);
                Edge backward = edgeLookup.get(v).get(u);
                forward.flow += bottleneck;
                backward.flow -= bottleneck;
                v = u;
            }

            printGraph();
            iteration++;
        }
        return maxFlow;
    }

    private int bfs(String source, String sink, Map<String, String> parent) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String u = queue.poll();
            for (Edge e : adj.get(u)) {
                String v = e.to;
                if (!visited.contains(v) && e.residualCapacity() > 0) {
                    visited.add(v);
                    parent.put(v, u);
                    if (v.equals(sink)) {
                        int bottleneck = Integer.MAX_VALUE;
                        String cur = v;
                        while (!cur.equals(source)) {
                            String prev = parent.get(cur);
                            Edge edge = edgeLookup.get(prev).get(cur);
                            bottleneck = Math.min(bottleneck, edge.residualCapacity());
                            cur = prev;
                        }
                        return bottleneck;
                    }
                    queue.add(v);
                }
            }
        }
        return 0;
    }

    public void printGraph() {
        System.out.println("Flow/Capacity Graph:");
        for (String node : adj.keySet()) {
            for (Edge e : adj.get(node)) {
                if (e.capacity > 0) {
                    System.out.println(e);
                }
            }
        }
        System.out.println();
    }
}

public class FordFolkersonInstance2 {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge("S", "A", 10);
        g.addEdge("S", "B", 10);
        g.addEdge("A", "C", 9);
        g.addEdge("A", "D", 8);
        g.addEdge("A", "B", 2);
        g.addEdge("C", "B", 1);
        g.addEdge("B", "E", 7);
        g.addEdge("C", "D", 5);
        g.addEdge("C", "T", 3);
        g.addEdge("D", "T", 9);
        g.addEdge("E", "T", 12);

        int maxFlow = g.fordFulkerson("S", "T");
        System.out.println("Max Flow = " + maxFlow);
    }
}
