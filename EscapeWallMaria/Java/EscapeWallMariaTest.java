package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EscapeWallMariaTest {

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
                        "2 4 4\n" +
                                "1111\n" +
                                "1S01\n" +
                                "1011\n" +
                                "0U11\n",
                        "2"
                ),
                new TestCase(
                        "Sample Test 2",
                        "2 4 4\n" +
                                "1111\n" +
                                "1S01\n" +
                                "1011\n" +
                                "0L11\n",
                        "NOT POSSIBLE"
                ),
                new TestCase(
                        "Sample Test 3",
                        "1 4 4\n" +
                                "1S01\n" +
                                "1001\n" +
                                "1011\n" +
                                "0U11\n",
                        "0"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testEscapeScenarios(TestCase testCase) throws IOException {
        assertEquals(testCase.expectedOutput.trim(), runWithInput(testCase.input).trim());
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) throws IOException {
        // Save original System streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set up test input/output
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();

            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute main method
            EscapeWallMaria.main(new String[]{});

            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}