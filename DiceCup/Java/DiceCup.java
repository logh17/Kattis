// Problem: Dice Cup
// URL: https://open.kattis.com/problems/dicecup

package Java;

import java.util.*;

public class DiceCup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Number of faces on first die
        int M = scanner.nextInt(); // Number of faces on second die
        scanner.close();

        // Calculate all possible sums and their frequencies
        Map<Integer, Integer> sumCounts = new HashMap<>();
        int maxCount = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int sum = i + j;
                sumCounts.put(sum, sumCounts.getOrDefault(sum, 0) + 1);
                maxCount = Math.max(maxCount, sumCounts.get(sum));
            }
        }

        // Collect all sums with maximum frequency
        List<Integer> mostLikelySums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostLikelySums.add(entry.getKey());
            }
        }

        // Sort the sums in ascending order
        Collections.sort(mostLikelySums);

        // Output the results
        for (int sum : mostLikelySums) {
            System.out.println(sum);
        }
    }
}