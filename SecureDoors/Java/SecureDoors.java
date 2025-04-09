// Problem: Secure Doors
// URL: https://open.kattis.com/problems/securedoors

package Java;

import java.util.*;

public class SecureDoors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of log entries (1 ≤ n ≤ 1000)
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline after reading the integer

        // Set to track people currently inside the building
        Set<String> insideBuilding = new HashSet<>();

        for (int i = 0; i < n; i++) {
            // Split each line into action ("entry"/"exit") and name
            String[] parts = scanner.nextLine().split(" ");
            String action = parts[0];
            String name = parts[1];

            boolean isAnomaly = false; // Flag to track if this action is anomalous

            if (action.equals("entry")) {
                // Check if person is already in building (anomalous entry)
                if (insideBuilding.contains(name)) {
                    isAnomaly = true;
                }
                // Update building status - person is now inside
                insideBuilding.add(name);
                // Print result with anomaly marker if needed
                System.out.printf("%s entered%s%n", name, isAnomaly ? " (ANOMALY)" : "");

            } else if (action.equals("exit")) {
                // Check if person is not in building (anomalous exit)
                if (!insideBuilding.contains(name)) {
                    isAnomaly = true;
                }
                // Update building status - person is now outside
                insideBuilding.remove(name);
                // Print result with anomaly marker if needed
                System.out.printf("%s exited%s%n", name, isAnomaly ? " (ANOMALY)" : "");
            }
        }
        scanner.close();
    }
}