// Problem: Cooking Water
// URL: https://open.kattis.com/problems/cookingwater

package Java;

import java.util.Scanner;

public class CookingWater {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSessions = scanner.nextInt(); // Number of boiling sessions

        if (numSessions == 0) {
            System.out.println("gunilla has a point"); // Edge case: no sessions, trivially true
            scanner.close();
            return;
        }

        int latestStart = Integer.MIN_VALUE; // Tracks the latest start time of all intervals
        int earliestEnd = Integer.MAX_VALUE; // Tracks the earliest end time of all intervals

        // Process each session to find the intersection of all intervals
        for (int i = 0; i < numSessions; i++) {
            int start = scanner.nextInt(); // Start of the interval when Edward looked away
            int end = scanner.nextInt();   // End of the interval when Edward looked away

            latestStart = Math.max(latestStart, start); // Update the latest start time
            earliestEnd = Math.min(earliestEnd, end);   // Update the earliest end time
        }
        scanner.close();

        // If the latest start time is after the earliest end time, no common second exists
        if (latestStart > earliestEnd) {
            System.out.println("edward is right");
        } else {
            // Otherwise, there is at least one common second where the pot could have boiled
            System.out.println("gunilla has a point");
        }
    }
}