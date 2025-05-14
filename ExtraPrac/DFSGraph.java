import java.util.*;

public class DFSGraph {
    enum Color { WHITE, GRAY, BLACK }

    static class Vertex {
        String name;
        Color color = Color.WHITE;
        int discoveryTime = -1;
        int finishTime = -1;
        Vertex parent = null;

        Vertex(String name) {
            this.name = name;
        }
    }

    private Map<String, Vertex> vertices = new HashMap<>();
    private Map<String, List<String>> adj = new HashMap<>();
    private int time = 0;
    private List<String> discoveryOrder = new ArrayList<>();
    private List<String> finishOrder = new ArrayList<>();

    public void addEdge(String from, String to) {
        vertices.putIfAbsent(from, new Vertex(from));
        vertices.putIfAbsent(to, new Vertex(to));
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public void dfsFrom(String start) {
        for (Vertex v : vertices.values()) {
            v.color = Color.WHITE;
            v.parent = null;
        }
        time = 0;
        discoveryOrder.clear();
        finishOrder.clear();

        Vertex startVertex = vertices.get(start);
        if (startVertex != null && startVertex.color == Color.WHITE) {
            dfsVisit(startVertex);
        }

        for (String name : getSortedVertexNames()) {
            Vertex v = vertices.get(name);
            if (v.color == Color.WHITE) {
                dfsVisit(v);
            }
        }
    }

    private void dfsVisit(Vertex u) {
        time++;
        u.discoveryTime = time;
        u.color = Color.GRAY;
        discoveryOrder.add(u.name);

        List<String> neighbors = adj.getOrDefault(u.name, new ArrayList<>());
        Collections.sort(neighbors);  // ASCII order

        for (String vName : neighbors) {
            Vertex v = vertices.get(vName);
            if (v.color == Color.WHITE) {
                v.parent = u;
                dfsVisit(v);
            }
        }

        u.color = Color.BLACK;
        time++;
        u.finishTime = time;
        finishOrder.add(u.name);
    }

    private List<String> getSortedVertexNames() {
        List<String> names = new ArrayList<>(vertices.keySet());
        Collections.sort(names);
        return names;
    }

    public void printDiscoveryAndFinishOrder() {
        System.out.println("Discovery Order:");
        for (int i = 0; i < discoveryOrder.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, discoveryOrder.get(i));
        }

        System.out.println("\nFinish (Visited) Order:");
        for (int i = 0; i < finishOrder.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, finishOrder.get(i));
        }
    }

    public static void main(String[] args) {
        DFSGraph g = new DFSGraph();

        // Corrected edge list from your clarification:
        g.addEdge("s", "r");
        g.addEdge("s", "v");
        g.addEdge("s", "u");

        g.addEdge("r", "t");

        g.addEdge("u", "t");
        g.addEdge("u", "y");

        g.addEdge("v", "w");
        g.addEdge("v", "y");

        g.addEdge("w", "r");
        g.addEdge("w", "x");
        g.addEdge("w", "z");

        g.addEdge("y", "x");
        g.addEdge("x", "z");

        // Run DFS starting at 's'
        g.dfsFrom("s");
        g.printDiscoveryAndFinishOrder();
    }
} 