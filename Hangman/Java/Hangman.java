// Problem: Hangman
// URL: https://open.kattis.com/problems/hangman

package Java;

import java.util.Scanner;

public class Hangman {
    // Game constants
    private static final int MAX_INCORRECT_GUESSES = 10;  // Max wrong guesses before losing
    private static final char HIDDEN_CHAR = '_';          // Character to hide guessed letters

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read input - target word and guess sequence
        String targetWord = scanner.nextLine().trim();
        String guessSequence = scanner.nextLine().trim();
        scanner.close();

        playHangman(targetWord, guessSequence);
    }

    private static void playHangman(String targetWord, String guessSequence) {
        int remainingLetters = targetWord.length();  // Track letters left to guess
        int wrongGuesses = 0;                       // Count of incorrect guesses
        char[] wordState = targetWord.toCharArray(); // Current state of word

        // Process each guess in sequence
        for (char guess : guessSequence.toCharArray()) {
            boolean correctGuess = false;

            // Check each letter in target word
            for (int i = 0; i < wordState.length; i++) {
                if (wordState[i] == guess) {
                    wordState[i] = HIDDEN_CHAR;  // Hide correctly guessed letter
                    remainingLetters--;
                    correctGuess = true;
                }
            }

            // Handle game outcome conditions
            if (correctGuess) {
                if (remainingLetters == 0) {  // All letters guessed
                    System.out.println("WIN");
                    return;
                }
            } else {
                wrongGuesses++;
                if (wrongGuesses >= MAX_INCORRECT_GUESSES) {  // Too many wrong guesses
                    System.out.println("LOSE");
                    return;
                }
            }
        }

        // If we exhaust all guesses without winning
        System.out.println("LOSE");
    }
}