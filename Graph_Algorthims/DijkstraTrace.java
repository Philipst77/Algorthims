import java.util.*;

public class DijkstraTrace {
    static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for (String v : List.of("A", "B", "C", "D", "E", "F", "G", "H"))
            graph.put(v, new ArrayList<>());

        // ✅ Correct edges from the updated diagram
        graph.get("A").add(new Edge("B", 1));
        graph.get("A").add(new Edge("E", 4));
        graph.get("A").add(new Edge("F", 8));

        graph.get("B").add(new Edge("C", 2));
        graph.get("B").add(new Edge("F", 6));
        graph.get("B").add(new Edge("G", 6));

        graph.get("C").add(new Edge("D", 1));
        graph.get("C").add(new Edge("G", 2));

        graph.get("D").add(new Edge("G", 1));
        graph.get("D").add(new Edge("H", 4));

        graph.get("E").add(new Edge("F", 5));

        // ❌ F has no outgoing edges

        graph.get("G").add(new Edge("H", 1));
        graph.get("G").add(new Edge("F", 1)); // ✅ newly added G → F

        // Distance map
        Map<String, Integer> dist = new HashMap<>();
        for (String v : graph.keySet()) dist.put(v, INF);
        dist.put("A", 0); // source

        Comparator<String> comparator = (a, b) -> {
            int cmp = Integer.compare(dist.get(a), dist.get(b));
            return (cmp != 0) ? cmp : a.compareTo(b);
        };

        PriorityQueue<String> pq = new PriorityQueue<>(comparator);
        pq.add("A");

        Set<String> visited = new HashSet<>();
        List<String> order = new ArrayList<>(graph.keySet());
        Collections.sort(order); // alphabetical header

        // Print header
        System.out.println("Dequeued\tA\tB\tC\tD\tE\tF\tG\tH");

        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (visited.contains(u)) continue;
            visited.add(u);

            for (Edge e : graph.get(u)) {
                int newDist = dist.get(u) + e.weight;
                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    pq.add(e.to);
                }
            }

            // Print table row
            System.out.print(u + "\t\t");
            for (String v : order) {
                int d = dist.get(v);
                System.out.print((d == INF ? "∞" : d) + "\t");
            }
            System.out.println();
        }
    }
}
