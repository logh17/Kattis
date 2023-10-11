// https://open.kattis.com/problems/cookingwater

// ola was here

import java.util.Scanner;

public class CookingWater {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] start = new int[n];
        int[] end = new int [n];
        for (int i = 0; i < n; i++) {
            start[i] = scanner.nextInt();
            end[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for ( int j = 0; j < n; j++) {
                if (start[i] > end[j]) {
                    System.out.println("edward is right");
                    return;
                }
            }
        }
        System.out.println("gunilla has a point");
    }
}
