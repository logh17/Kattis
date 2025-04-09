// Problem: Heliocentric
// URL: https://open.kattis.com/problems/heliocentric

package Java;

import java.util.Scanner;

public class Heliocentric {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;  // Track test case numbers starting from 1

        // Constants for planetary orbital periods (in Earth days)
        final int EARTH_PERIOD = 365;
        final int MARS_PERIOD = 687;

        while (scanner.hasNext()) {
            int earthDay = scanner.nextInt();  // Earth's current day (0-364)
            int marsDay = scanner.nextInt();   // Mars' current day (0-686)

            int days = 0;  // Counter for days until alignment

            // Continue until both planets are at day 0
            while (earthDay != 0 || marsDay != 0) {
                // Advance both planets by one day
                earthDay = (earthDay + 1) % EARTH_PERIOD;  // Wrap around using modulo
                marsDay = (marsDay + 1) % MARS_PERIOD;     // Wrap around using modulo
                days++;  // Increment day counter
            }
            System.out.printf("Case %d: %d%n", caseNumber++, days);
        }
        scanner.close();
    }
}