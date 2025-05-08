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
        for (String v : List.of("A", "B", "C", "D", "E"))
            graph.put(v, new ArrayList<>());

        graph.get("A").add(new Edge("C", 2));
        graph.get("A").add(new Edge("B", 4));

        graph.get("B").add(new Edge("C", 3));
        graph.get("B").add(new Edge("E", 3));
        graph.get("B").add(new Edge("D", 2));

        graph.get("C").add(new Edge("B", 1));
        graph.get("C").add(new Edge("D", 4));
        graph.get("C").add(new Edge("E", 5));

        graph.get("E").add(new Edge("D", 1));

        Map<String, Integer> dist = new HashMap<>();
        for (String v : graph.keySet()) dist.put(v, INF);
        dist.put("A", 0);

        Comparator<String> comparator = (a, b) -> {
            int cmp = Integer.compare(dist.get(a), dist.get(b));
            return (cmp != 0) ? cmp : a.compareTo(b);
        };

        PriorityQueue<String> pq = new PriorityQueue<>(comparator);
        pq.add("A");

        Set<String> visited = new HashSet<>();
        List<String> order = new ArrayList<>(graph.keySet());
        Collections.sort(order);

        // Print only column headers
        for (String v : order) {
            System.out.print(v + "\t");
        }
        System.out.println();

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

            for (String v : order) {
                int d = dist.get(v);
                System.out.print((d == INF ? "∞" : d) + "\t");
            }
            System.out.println();
        }
    }
}
