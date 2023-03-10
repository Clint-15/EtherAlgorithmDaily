package les09.algorithm;

public class Knapsack {

    public static int process(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }

        if (i == weights.length) {
            return 0;
        }

        return Math.max(0 + process(weights, values, i + 1, alreadyweight, bag),
                weights[i] + process(weights, values, i + 1, weights[i] + alreadyweight, bag));
    }
}
