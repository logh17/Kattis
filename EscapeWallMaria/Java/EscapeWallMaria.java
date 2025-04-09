// Problem: Escape Wall Maria
// URL: https://open.kattis.com/problems/escapewallmaria

package Java;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EscapeWallMaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int timeLimit = scanner.nextInt();  // T: time before titans arrive
        int rows = scanner.nextInt();      // N: grid rows
        int cols = scanner.nextInt();      // M: grid columns

        char[][] grid = new char[rows][cols];
        Position start = null;

        // Read grid input line by line
        for (int r = 0; r < rows; r++) {
            String line = scanner.next();
            for (int c = 0; c < cols; c++) {
                grid[r][c] = line.charAt(c);
                if (grid[r][c] == 'S') start = new Position(r, c);  // Mark start position
            }
        }
        scanner.close();

        int escapeTime = findEscapeTime(grid, start, timeLimit);
        System.out.println(escapeTime <= timeLimit ? escapeTime : "NOT POSSIBLE");
    }

    public static int findEscapeTime(char[][] grid, Position start, int timeLimit) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Movement directions: up, down, right, left
        int[] rowDir = {-1, 1, 0, 0};
        int[] colDir = {0, 0, 1, -1};

        boolean[][] visited = new boolean[rows][cols];  // Track visited positions
        Queue<Position> queue = new LinkedList<>();     // BFS queue
        queue.add(start);
        visited[start.row][start.col] = true;
        int time = 0;  // Current time units elapsed

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Nodes at current BFS level

            // Process all nodes at current time level
            for (int i = 0; i < levelSize; i++) {
                Position current = queue.poll();

                // Check if reached grid boundary (escape successful)
                if (current.row == 0 || current.row == rows-1 ||
                        current.col == 0 || current.col == cols-1) {
                    return time;
                }

                // Explore all four possible directions
                for (int d = 0; d < 4; d++) {
                    int newRow = current.row + rowDir[d];
                    int newCol = current.col + colDir[d];

                    // Check if move is valid and not visited
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                            !visited[newRow][newCol] && canMove(grid[newRow][newCol], rowDir[d], colDir[d])) {
                        queue.add(new Position(newRow, newCol));
                        visited[newRow][newCol] = true;
                    }
                }
            }
            time++;  // Increment time after processing each level
            if (time > timeLimit) break;  // Early exit if exceeded time limit
        }
        return Integer.MAX_VALUE;  // No escape path found
    }

    // Checks if movement to a tile is allowed based on direction
    private static boolean canMove(char tile, int rowDir, int colDir) {
        return switch (tile) {
            case '0', 'S' -> true;   // Always passable
            case '1' -> false;       // Blocked by fire
            case 'U' -> rowDir == 1; // Must come from below (moving up)
            case 'D' -> rowDir == -1;// Must come from above (moving down)
            case 'L' -> colDir == 1; // Must come from right (moving left)
            case 'R' -> colDir == -1;// Must come from left (moving right)
            default -> false;        // Unknown tile type
        };
    }
}

// Simple class to store grid positions
class Position {
    int row, col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}