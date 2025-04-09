package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiceCupTest {

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
                        "6 6\n",
                        "7\n"
                ),
                new TestCase(
                        "Sample Test 2",
                        "6 4\n",
                        "5\n6\n7\n"
                ),
                new TestCase(
                        "Sample Test 3",
                        "12 20\n",
                        "13\n14\n15\n16\n17\n18\n19\n20\n21\n"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testDiceCup(TestCase testCase) throws IOException {
        String expected = testCase.expectedOutput.replace("\r\n", "\n").trim();
        String actual = runWithInput(testCase.input).replace("\r\n", "\n").trim();
        assertEquals(expected, actual);
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) throws IOException {
        // Save original system streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up test input/output
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();

            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute main method
            DiceCup.main(new String[]{});

            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}