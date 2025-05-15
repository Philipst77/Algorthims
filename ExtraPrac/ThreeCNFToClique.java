import java.util.*;

public class ThreeCNFToClique {

    static class Vertex {
        String clauseId;
        String literal;

        Vertex(String clauseId, String literal) {
            this.clauseId = clauseId;
            this.literal = literal;
        }

        @Override
        public String toString() {
            return clauseId + ":" + literal;
        }
    }

    public static void main(String[] args) {
        String[][] clauses = {
            {"x1", "!x2", "!x3"},   // Clause 1
            {"!x1", "x2", "x3"},    // Clause 2
            {"x1", "x2", "x3"}      // Clause 3
        };

        List<Vertex> vertices = new ArrayList<>();
        Map<String, List<Vertex>> clauseGroups = new HashMap<>();

        for (int i = 0; i < clauses.length; i++) {
            String clauseId = "C" + (i + 1);
            clauseGroups.put(clauseId, new ArrayList<>());
            for (String literal : clauses[i]) {
                Vertex v = new Vertex(clauseId, literal);
                vertices.add(v);
                clauseGroups.get(clauseId).add(v);
            }
        }

        Set<String> edges = new HashSet<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                Vertex v1 = vertices.get(i);
                Vertex v2 = vertices.get(j);

                if (v1.clauseId.equals(v2.clauseId)) continue;

                if (v1.literal.equals("!" + v2.literal) || v2.literal.equals("!" + v1.literal)) continue;

                edges.add(v1 + " -- " + v2);
            }
        }

        System.out.println("Q1: How many vertices does the instance of clique have?");
        System.out.println("Total vertices: " + vertices.size());

        System.out.println("\nQ2: How many edges are in the instance of clique?");
        System.out.println("Total edges: " + edges.size());

        System.out.println("\nQ3: How many edges come from v1 (x1 in Clause 1)?");
        for (String e : edges)
            if (e.startsWith("C1:x1"))
                System.out.println(e);

        System.out.println("\nQ4: Which vertices are connected to ¬v2 (¬x2 in Clause 1)?");
        for (Vertex v : vertices) {
            if (!v.clauseId.equals("C1") && !v.literal.equals("x2") && !v.literal.equals("!x2")) {
                if (!v.literal.equals("x2")) {
                    String edge = "C1:!x2 -- " + v;
                    String reverse = v + " -- C1:!x2";
                    if (edges.contains(edge) || edges.contains(reverse))
                        System.out.println(v);
                }
            }
        }

        System.out.println("\nQ5: Which edges are not valid when connecting Clause 1 with Clause 2?");
        for (Vertex v1 : clauseGroups.get("C1")) {
            for (Vertex v2 : clauseGroups.get("C2")) {
                if (v1.literal.equals("!" + v2.literal) || v2.literal.equals("!" + v1.literal)) {
                    System.out.println(v1 + " -- " + v2);
                }
            }
        }
    }
}
