import java.util.HashSet;
// X(n+1) = (a * Xn * c) mod m
public class Random {
    private static final long A = 1664525;
    private static final long C = 1013904223;
    private static final long M = 4294967296L;

    private long seed;
    private final HashSet<Double> generatedNumbers = new HashSet<>();

    public Random() {
        this.seed = System.currentTimeMillis() + (int) (System.nanoTime() % Integer.MAX_VALUE);
    }

    // Génère un nombre aléatoire entre 0 et 1
    private double nextDouble() {
        seed = (A * seed + C) % M;
        double result =  (double) seed / M;
        while (result == 0.0 || result == 1.0) {
            seed = (A * seed + C) % M;
            result = (double) seed / M;
        }
        return result;
    }
        public double random ( int a, int b){
            if (a > b) {
                throw new IllegalArgumentException("a must be less than or equal to b");
            }
            if ((b - a + 1) == generatedNumbers.size()) {
                throw new IllegalStateException("All numbers between a and b have been generated");
            }
            int range = b-a;
            double generated;
            do {
                generated = a + (nextDouble() * range);
            } while (generatedNumbers.contains(generated));
            generatedNumbers.add(generated);
            return generated;
        }
    }