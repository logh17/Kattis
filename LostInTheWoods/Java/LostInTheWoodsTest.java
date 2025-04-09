package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LostInTheWoodsTest {

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
                        "3 3\n0 1\n1 2\n0 2\n",
                        "2.000000"
                ),
                new TestCase(
                        "Sample Test 2",
                        "5 6\n0 1\n0 2\n1 2\n2 4\n0 3\n3 4\n",
                        "6.727273"
                ),
                new TestCase(
                        "Sample Test 3",
                        "4 4\n0 1\n1 3\n3 0\n0 2\n",
                        "3.333333"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testLostInTheWoods(TestCase testCase) throws IOException {
        assertEquals(testCase.expectedOutput.trim(), runWithInput(testCase.input).trim());
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
            LostInTheWoods.main(new String[]{});

            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}