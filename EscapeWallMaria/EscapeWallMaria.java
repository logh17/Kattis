// https://open.kattis.com/problems/escapewallmaria

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EscapeWallMaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] grid = new char[n][m];
        Point start = null;
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {
                    start = new Point(i, j);
                }
            }
        }
        int result = escape(grid, start, t);
        if (result == -1) {
            System.out.println("NOT POSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    public static int escape(char[][] grid, Point start, int t) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                if (time > t) {
                    return -1;
                }
                if (current.r == 0 || current.r == n-1 || current.c == 0 || current.c == m-1) {
                    return time;
                }
                for (int j = 0; j < 4; j++) {
                    int newR = current.r + dr[j];
                    int newC = current.c + dc[j];
                    char newTile = grid[newR][newC];
                    switch(newTile) {
                        case '0':
                            if (newR >= 0 && newR < n && newC >= 0 && newC < m && !visited[newR][newC]) {
                                queue.add(new Point(newR, newC));
                                visited[newR][newC] = true;
                            }
                            break;
                        case '1':
                            visited[newR][newC] = true;
                            break;
                        case 'U':
                            if (j == 1) {
                                if (newR >= 0 && newR < n && newC >= 0 && newC < m && !visited[newR][newC]) {
                                    queue.add(new Point(newR, newC));
                                    visited[newR][newC] = true;
                                }
                            }
                            break;
                        case 'D':
                            if (j == 0) {	
                                if (newR >= 0 && newR < n && newC >= 0 && newC < m && !visited[newR][newC]) {
                                    queue.add(new Point(newR, newC));
                                    visited[newR][newC] = true;
                                }
                            }
                            break;
                        case 'L':
                            if (j == 2) {	
                                if (newR >= 0 && newR < n && newC >= 0 && newC < m && !visited[newR][newC]) {
                                    queue.add(new Point(newR, newC));
                                    visited[newR][newC] = true;
                                }
                            }
                            break;
                        case 'R':
                            if (j == 3) {	
                                if (newR >= 0 && newR < n && newC >= 0 && newC < m && !visited[newR][newC]) {
                                    queue.add(new Point(newR, newC));
                                    visited[newR][newC] = true;
                                }
                            }
                            break;
                        case 'S':
                            visited[newR][newC] = true;
                            break;
                        default:
                            break;
                    }
                }
            }
            time++;
        }
        return -1;
    }
}

class Point {
    int r, c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
