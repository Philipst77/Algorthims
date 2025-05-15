import java.util.*;

public class BFSgraph {
    enum Color { WHITE, GRAY, BLACK }

    static class Vertex {
        String name;
        Color color = Color.WHITE;
        int distance = Integer.MAX_VALUE;
        Vertex parent = null;

        Vertex(String name) {
            this.name = name;
        }
    }

    private Map<String, Vertex> vertices = new HashMap<>();
    private Map<String, List<String>> adj = new HashMap<>();
    private List<String> discoveryOrder = new ArrayList<>();

    public void addEdge(String from, String to) {
        vertices.putIfAbsent(from, new Vertex(from));
        vertices.putIfAbsent(to, new Vertex(to));
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public void bfsFrom(String start) {
        for (Vertex v : vertices.values()) {
            v.color = Color.WHITE;
            v.distance = Integer.MAX_VALUE;
            v.parent = null;
        }
        discoveryOrder.clear();

        Vertex s = vertices.get(start);
        s.color = Color.GRAY;
        s.distance = 0;
        s.parent = null;

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(s);
        discoveryOrder.add(s.name);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            List<String> neighbors = new ArrayList<>(adj.getOrDefault(u.name, new ArrayList<>()));
            Collections.sort(neighbors);  // ASCII order

            for (String vName : neighbors) {
                Vertex v = vertices.get(vName);
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.distance = u.distance + 1;
                    v.parent = u;
                    queue.add(v);
                    discoveryOrder.add(v.name);
                }
            }

            u.color = Color.BLACK;
        }
    }

    public void printDiscoveryOrder() {
        System.out.println("Discovery (Visited) Order:");
        for (int i = 0; i < discoveryOrder.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, discoveryOrder.get(i));
        }
    }

    public static void main(String[] args) {
        BFSgraph g = new BFSgraph();

        // Graph edges
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

        // Run BFS from 's'
        g.bfsFrom("s");
        g.printDiscoveryOrder();
    }
}
