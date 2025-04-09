// Problem: Lost In The Woods
// URL: https://open.kattis.com/problems/lostinthewoods

package Java;

import java.util.*;

public class LostInTheWoods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numClearings = scanner.nextInt();  // Number of clearings (N)
        int numPaths = scanner.nextInt();      // Number of paths (M)

        // Build adjacency list for the forest graph
        List<List<Integer>> forestGraph = new ArrayList<>(numClearings);
        for (int i = 0; i < numClearings; i++) {
            forestGraph.add(new ArrayList<>());
        }

        // Read and store all paths between clearings
        for (int i = 0; i < numPaths; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            forestGraph.get(from).add(to);
            forestGraph.get(to).add(from);  // Paths are bidirectional
        }
        scanner.close();

        // Calculate expected time to exit
        double expectedTime = calculateExpectedExitTime(forestGraph, numClearings);

        // Print result with 6 decimal precision
        System.out.printf(Locale.US, "%.6f%n", expectedTime);
    }

    private static double calculateExpectedExitTime(List<List<Integer>> graph, int numClearings) {
        double[] currentProb = new double[numClearings];
        double[] previousProb = new double[numClearings];
        currentProb[0] = 1.0;  // Starting at clearing 0
        double expectedTime = 0.0;
        final int exitClearing = numClearings - 1;

        // Simulate random walk for sufficient iterations
        for (int minute = 1; minute <= 10000; minute++) {
            // Store current probabilities before updating
            System.arraycopy(currentProb, 0, previousProb, 0, numClearings);
            Arrays.fill(currentProb, 0.0);

            // Update probabilities for each clearing
            for (int current = 0; current < exitClearing; current++) {
                if (previousProb[current] > 0) {
                    int neighbors = graph.get(current).size();
                    double moveProb = previousProb[current] / neighbors;

                    // Distribute probability to neighboring clearings
                    for (int neighbor : graph.get(current)) {
                        currentProb[neighbor] += moveProb;
                    }
                }
            }

            // Add to expected time if reached exit
            expectedTime += currentProb[exitClearing] * minute;
        }

        return expectedTime;
    }
}