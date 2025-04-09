package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PachydermPeanutPackingTest {

    // Test case record to hold input and expected output
    record TestCase(String description, String input, String expectedOutput) {
        @Override
        public String toString() {
            return description; // Used for test display names
        }
    }

    // Provides test cases for parameterized testing
    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        "Sample Test 1",
                        "2\n" +
                                "0 0 1 1 small\n" +
                                "3 2 6 5 large\n" +
                                "3\n" +
                                "2 0 small\n" +
                                "0.5 0.6 medium\n" +
                                "3 4 large\n" +
                                "3\n" +
                                "60.0 2.9 111.4 32.9 medium\n" +
                                "20.0 82.6 43.4 153.1 small\n" +
                                "34.7 9.6 56.6 78.2 large\n" +
                                "6\n" +
                                "60.0 8.3 small\n" +
                                "43.4 153.1 small\n" +
                                "13.4 55.9 medium\n" +
                                "61.5 68.1 medium\n" +
                                "72.1 69.1 large\n" +
                                "78.4 13.2 large\n" +
                                "0\n",
                        "small floor\n" +
                                "medium small\n" +
                                "large correct\n" +
                                "\n" +
                                "small medium\n" +
                                "small correct\n" +
                                "medium floor\n" +
                                "medium floor\n" +
                                "large floor\n" +
                                "large medium\n"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testPachydermPeanutPacking(TestCase testCase) throws IOException {
        String expected = testCase.expectedOutput.replace("\r\n", "\n").trim();
        String actual = runWithInput(testCase.input).replace("\r\n", "\n").trim();
        assertEquals(expected, actual);
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) throws IOException {
        // Save original system streams for restoration later
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up test input stream
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            // Set up output capture stream
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();

            // Redirect system streams
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute the main program
            PachydermPeanutPacking.main(new String[]{});

            // Return captured output
            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}