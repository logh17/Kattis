// https://open.kattis.com/problems/lostinthewoods

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LostInTheWoods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Integer>> neighbours = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbours.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int k = scanner.nextInt();
            int l = scanner.nextInt();
            neighbours.get(k).add(l);
            neighbours.get(l).add(k);
        }
        List<Double> probability = new ArrayList<>();
        List<Double> oldProbability = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            probability.add(0.0);
            oldProbability.add(0.0);
        }
        probability.set(0, 1.0);
        double time = 0;
        for (int i = 1; i < 10001; i++) {
            oldProbability = new ArrayList<>(probability);
            probability = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                probability.add(0.0);
            }
            for (int j = 0; j < n - 1; j++) {
                for (Integer k : neighbours.get(j)) {
                    probability.set(k.intValue(), probability.get(k.intValue()) + oldProbability.get(j) / neighbours.get(j).size());
                }
            }
            time += probability.get(n - 1) * i;
        }
        System.out.printf("%.6f", time);
    }
}
