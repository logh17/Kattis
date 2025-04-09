package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanTest {

    // Test case record to hold input and expected output
    record TestCase(String testName, String input, String expectedOutput) {
        @Override
        public String toString() {
            return testName; // Used for test display names
        }
    }

    // Provides test cases for parameterized testing
    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        "Sample Test 1",
                        "HANGMAN\n" +
                                "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n",
                        "WIN"
                ),
                new TestCase(
                        "Sample Test 2",
                        "BANANA\n" +
                                "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n",
                        "LOSE"
                ),
                new TestCase(
                        "Sample Test 3",
                        "RAINBOWS\n" +
                                "USIANBVLOJRKWXZCTQGHPFMYDE\n",
                        "WIN"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testHangman(TestCase testCase) throws IOException {
        assertEquals(testCase.expectedOutput.trim(), runWithInput(testCase.input).trim());
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) throws IOException {
        // Save original system streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up test streams
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();

            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute the game
            Hangman.main(new String[]{});

            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}