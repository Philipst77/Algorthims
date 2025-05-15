import java.util.*;

public class CNFtoSubsetSum {

    static class LiteralVector {
        String name;
        int[] vector;

        LiteralVector(String name, int size) {
            this.name = name;
            this.vector = new int[size];
        }

        void setBit(int index) {
            vector[index] = 1;
        }

        @Override
        public String toString() {
            return name + " : " + Arrays.toString(vector);
        }
    }

    public static void main(String[] args) {
        int numVars = 3;
        int numClauses = 3;
        int totalSize = numVars + numClauses; // x1, x2, x3 | C1, C2, C3

        List<LiteralVector> vectors = new ArrayList<>();

        // Literal vectors based on clause inclusion
        vectors.add(makeVector("v1", totalSize, 0, 0)); // x1 in C1
        vectors.add(makeVector("v'1", totalSize, 0, 1)); // ~x1 in C2
        vectors.add(makeVector("v2", totalSize, 1, 0)); // x2 in C1
        vectors.add(makeVector("v'2", totalSize, 1, 2)); // ~x2 in C3
        vectors.add(makeVector("v3", totalSize, 2, 1)); // x3 in C2
        vectors.add(makeVector("v'3", totalSize, 2, 0)); // ~x3 in C1

        // Print header
        System.out.print("     ");
        for (int i = 1; i <= numVars; i++) {
            System.out.print("x" + i + " ");
        }
        for (int i = 1; i <= numClauses; i++) {
            System.out.print("C" + i + " ");
        }
        System.out.println();

        // Print matrix
        for (LiteralVector vec : vectors) {
            System.out.printf("%-4s ", vec.name);
            for (int v : vec.vector) {
                System.out.print(v + "  ");
            }
            System.out.println();
        }

        // Print target
        System.out.print("t    ");
        for (int i = 0; i < numVars; i++) System.out.print("1  ");
        for (int i = 0; i < numClauses; i++) System.out.print("1  ");
        System.out.println();
    }

    static LiteralVector makeVector(String name, int size, int varIndex, int clauseIndex) {
        LiteralVector vec = new LiteralVector(name, size);
        vec.setBit(varIndex); // variable column
        vec.setBit(size - 3 + clauseIndex); // clause column (last 3 bits)
        return vec;
    }
} 
