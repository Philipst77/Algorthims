import java.util.HashMap;

public class FibonacciTopDown {
    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static long Fib(int n) {
        if (n <= 1) return n;  // Base case
        if (memo.containsKey(n)) return memo.get(n); // Check memoization

        // Recursively compute Fib(n) and store it
        long result = Fib(n - 1) + Fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int[] testValues = {50, 100, 1000, 10000, 50000};

        for (int n : testValues) {
            memo.clear();  
            long startTime = System.nanoTime();
            long result = Fib(n);
            long endTime = System.nanoTime();
            double elapsedTime = (endTime - startTime) / 1e6; 

            System.out.printf("Fibonacci for n = %6d, f(n) = %s, time: %.4f ms.%n", n, result, elapsedTime);
        }
    }
}
