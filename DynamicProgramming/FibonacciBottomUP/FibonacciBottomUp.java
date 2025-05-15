import java.math.BigInteger;

public class FibonacciBottomUp {
    public static BigInteger Fib(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger prev = BigInteger.ZERO, curr = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger next = prev.add(curr);
            prev = curr;
            curr = next;
        }

        return curr; 
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci series with the bottom-up approach\n");

        int[] tests = {50, 100, 1000, 10000, 50000};

        for (int i : tests) {
            long t0 = System.nanoTime();
            BigInteger f = Fib(i);
            double t = (System.nanoTime() - t0) / 1_000_000.0;

            String formattedResult = f.toString().length() > 20 ? 
                String.format(" = %.6e", new java.math.BigDecimal(f)) : f.toString();

            System.out.printf("Fibonacci for n = %6d, f(n) %s, time: %.4f ms.\n", i, formattedResult, t);
        }
    }
}
