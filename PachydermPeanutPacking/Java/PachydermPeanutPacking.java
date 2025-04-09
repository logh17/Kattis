// Problem: Pachyderm Peanut Packing
// URL: https://open.kattis.com/problems/pachydermpeanutpacking

package Java;

import java.util.*;
import java.io.*;

public class PachydermPeanutPacking {
    // Inner class to represent each box's coordinates and expected peanut size
    static class Box {
        double x1, y1, x2, y2;  // Bottom-left (x1,y1) and top-right (x2,y2) coordinates
        String size;             // Expected size of peanuts for this box

        public Box(double x1, double y1, double x2, double y2, String size) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.size = size;
        }

        // Check if given coordinates fall within this box (inclusive of edges)
        boolean contains(double x, double y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }

    public static void main(String[] args) throws IOException {
        // Set up buffered I/O for faster input/output processing
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String line;            // Current line being read
        boolean firstCase = true; // Track first case for proper blank line formatting

        // Process each test case until termination condition (n=0)
        while ((line = br.readLine()) != null) {
            // Read number of boxes (n) and check for termination
            int n = Integer.parseInt(line.trim());
            if (n == 0) break;

            // Print blank line between cases (except first one)
            if (!firstCase) {
                pw.println();
            }
            firstCase = false;

            // Read and store all box descriptions
            List<Box> boxes = new ArrayList<>(n); // Pre-size for efficiency
            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().trim().split(" ");
                double x1 = Double.parseDouble(parts[0]);
                double y1 = Double.parseDouble(parts[1]);
                double x2 = Double.parseDouble(parts[2]);
                double y2 = Double.parseDouble(parts[3]);
                boxes.add(new Box(x1, y1, x2, y2, parts[4]));
            }

            // Read number of peanuts to process
            int m = Integer.parseInt(br.readLine().trim());

            // Process each peanut
            for (int i = 0; i < m; i++) {
                String[] parts = br.readLine().trim().split(" ");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                String peanutSize = parts[2];
                String status = "floor"; // Default status if not in any box

                // Check each box to find where peanut belongs
                for (Box box : boxes) {
                    if (box.contains(x, y)) {
                        // Determine status based on size match
                        status = box.size.equals(peanutSize) ? "correct" : box.size;
                        break; // Peanut can only be in one box (no overlapping boxes)
                    }
                }

                // Output result for this peanut
                pw.println(peanutSize + " " + status);
            }
        }
        // Flush all output at once for better performance
        pw.flush();
    }
}