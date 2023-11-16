// https://open.kattis.com/problems/hangman

import java.util.Scanner;

public class Hangman {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        String alphabet = scanner.nextLine();
        int guesses = 0;
        Character c = Character.MIN_VALUE;
        for (int i = 0; i < alphabet.length(); i++) {
            if (word.indexOf(alphabet.charAt(i)) >= 0) {
                word = word.replace(alphabet.charAt(i), c);
                if (!containsLetter(word)) {
                    System.out.println("WIN");
                    break;
                }
            } else {
                guesses++;
                if (guesses >= 10) {
                    System.out.println("LOSE");
                    break;
                }
            }
        }
    }

    public static boolean containsLetter(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }
}
